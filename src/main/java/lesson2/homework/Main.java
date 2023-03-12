package lesson2.homework;

import lombok.Getter;

import java.util.*;

// 1. Создайте три класса: Человек, Кот, Робот, которые наследуются от одного класса. Эти классы должны уметь бегать и прыгать, все также с выводом информации о действии в консоль.
// 2. Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие действия (бежать или прыгать),
// результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
// У препятствий есть длина (для дорожки) или высота (для стены),а у участников ограничения на бег и прыжки.
// 3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий. Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.

public class Main {
    static ArrayList<Obstacle> obstacles = getObstacles();
    static ArrayList<Player> players = getPlayers();

    public static void main(String[] args) {
        while (true) {
            boolean allFinished = true;
            for (Player player : players) allFinished = player.getFinish() && allFinished;
            if (allFinished) break;
            players.forEach(Player::next);
            Player.getView(obstacles, players);
            // try {
            //     Thread.sleep(1000);
            // } catch (Exception e) {
            //     //
            // }
        }
    }

    private static ArrayList<Obstacle> getObstacles() {
        // TODO добавить рандомную генерацию
        return new ArrayList<>(List.of(
                new Track(30),
                new Wall(0.4),
                new Track(10),
                new Wall(0.6),
                new Track(20),
                new Wall(1.0),
                new Track(15),
                new Wall(0.2),
                new Track(40),
                new Wall(1.4),
                new Track(56),
                new Wall(0.8),
                new Track(140)
        ));
    }

    private static ArrayList<Player> getPlayers() {
        return new ArrayList<>(List.of(
                new People(obstacles),
                new Cat(obstacles),
                new Robot(obstacles)
        ));
    }
}

@Getter
abstract class Obstacle {
    private final double value; // размер препятсвия (длина дорожки/высота препятствия)

    public Obstacle(double value) {
        this.value = value;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }
}

class Track extends Obstacle {
    public Track(double value) {
        super(value);
    }
}

class Wall extends Obstacle {
    public Wall(double value) {
        super(value);
    }
}

abstract class Player {
    private static final int staminaUseRun = 100; // Сколько выносливости используется для бега
    private static final int staminaUseJump = 50; // Сколько выносливости используется для прыжка
    private double stamina = 100; // Выносливость
    private final double rangeRun; // Макс. дальность бега
    private final double rangeJump; // Макс. высота прыжка
    private final int staminaRecovery; // Восстановление выносливости за 1 действие
    private final Queue<Obstacle> queueObstacle; // Полоса припятствий
    private Obstacle lastObstacle = null; // Последнее препятствие
    private int step = 0; // Текуший шаг
    private double needStamina = 0; // Требуется выносливости
    private String lastMessage = null;
    private boolean finish = false; // Закончил

    public static void getView(ArrayList<Obstacle> queue, ArrayList<Player> players) {
        ArrayList<String[]> track = new ArrayList<>();
        boolean isWall;
        int size;
        String val;
        int step = 0;
        for (Obstacle obstacle : queue) {
            String[] line = new String[players.size() + 2];
            isWall = obstacle.getType().equals("Wall");
            size = isWall ? 1 : (int) Math.round(obstacle.getValue() / 20);
            line[0] = line[line.length - 1] = isWall
                    ? String.format("%" + (size * 2 + 1) + "s", " ").replace(" ", "#")
                    : String.format("%" + (size * 2 + 1) + "s", " ").replace(" ", "-");
            val = String.format("%" + size + "s_%" + size + "s", "", "");
            int idx = 1;
            for (Player player : players) {
                line[idx++] = player.getStep() == step
                        ? val.replace("_", String.valueOf(player.getType().toCharArray()[0]))
                        : val.replace("_", " ");
                //        : (isWall ? val.replace("_", "#") : val.replace("_", " "));
            }
            track.add(line);
            step++;
        }
        Player pl;
        for (int i = 0; i < players.size() + 2; i++) {
            for (String[] item : track) System.out.print(item[i]);
            if (i > 0 && i <= players.size()) {
                pl = players.get(i - 1);
                System.out.printf(
                        "%10s: [Выносливость %6.2f из требуемых %6.2f, Шаг: %2d, %s]\n",
                        pl.getType(),
                        pl.stamina,
                        pl.needStamina,
                        pl.step,
                        pl.lastMessage != null ? pl.lastMessage : (pl.finish ? "Закончил" : "В процессе")
                );
            } else {
                System.out.print("\n");
            }
        }
    }

    /**
     * @param rangeRun        - дистанция бега за 1пт. (staminaUseRun) выносливости
     * @param rangeJump       - высота прыжка
     * @param staminaRecovery - восстановление выносливости за шаг
     * @param queue           - полоса препятствий
     */
    public Player(double rangeRun, double rangeJump, int staminaRecovery, ArrayList<Obstacle> queue) {
        this.rangeRun = rangeRun;
        this.rangeJump = rangeJump;
        this.staminaRecovery = staminaRecovery;
        this.queueObstacle = new ArrayDeque<>(queue);
    }

    public void next() {
        if (this.lastObstacle == null) {
            if ((this.lastObstacle = this.queueObstacle.poll()) == null) {
                this.finish = true; // Устанавливаем последнее препятствие
            }
        }
        if (this.lastObstacle != null) {
            switch (this.lastObstacle.getType()) { // Действие
                case "Track" -> this.run(this.lastObstacle.getValue());
                case "Wall" -> this.jump(this.lastObstacle.getValue());
            }
        }
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public boolean getFinish() {
        return this.finish;
    }

    public int getStep() {
        return this.step;
    }

    private void run(double distance) {
        if (distance > this.rangeRun && !this.finish) {
            this.lastMessage = "Не смог пробежать (" + this.rangeRun + "/" + distance + ")";
            this.finish = true;
        }
        this.action(Player.staminaUseRun * (distance / this.rangeRun));
    }

    private void jump(double height) {
        if (height > this.rangeJump && !this.finish) {
            this.lastMessage = "Не смог перепрыгнуть (" + this.rangeJump + "/" + height + ")";
            this.finish = true; // Не смог перепрыгнуть
        }
        this.action(Player.staminaUseJump * (height / this.rangeJump));
    }

    private void action(double useStamina) {
        if (this.finish) return; // более не участвует
        this.needStamina = useStamina;
        if (useStamina <= this.stamina) { // выносливости хватает
            this.step++;
            this.stamina -= useStamina;
            this.lastObstacle = null; // Чистим последнее препятствие
        } else if (this.staminaRecovery == 0) {
            this.lastMessage = "Кончилась выносливость";
            this.finish = true; // Выносливость кончилась, не восстанавливается
        }
        if (this.stamina < 100) this.stamina += this.staminaRecovery; // восстановление выносливости после действия
        if (this.stamina > 100) this.stamina = 100;
    }
}

class People extends Player {
    public People(ArrayList<Obstacle> queue) {
        super(150, 1.2, 15, queue);
    }
}

class Cat extends Player {
    public Cat(ArrayList<Obstacle> queue) {
        super(250, 1.6, 20, queue);
    }
}

class Robot extends Player {
    public Robot(ArrayList<Obstacle> queue) {
        super(1000, 0.6, 0, queue);
    }
}