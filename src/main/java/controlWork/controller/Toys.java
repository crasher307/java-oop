package controlWork.controller;

import controlWork.model.*;
import lombok.Getter;

import java.util.*;

@Getter
public class Toys {
    private final List<Log> logs = new ArrayList<>();
    private static final Random rand = new Random();
    private final List<Integer> ids = new ArrayList<>();

    private static class ChanceComparator implements Comparator<Toy> {
        @Override
        public int compare(Toy left, Toy right) {
            return Double.compare(left.getChance(), right.getChance());
        }
    }

    private final static Comparator<Toy> comparator = new ChanceComparator();
    private final Queue<Toy> data = new PriorityQueue<>(10, Toys.comparator);
    private double allChances = 0;

    public double getAllChances() {
        return this.checkChance(this.allChances);
    }
    // *** Use ***

    /**
     * Рандом приза
     *
     * @return
     */
    public Toy getLot() {
        double luck;
        while (true) {
            luck = this.checkChance(rand.nextDouble());
            for (Toy item : this.data) {
                if (luck <= item.getFullChance()) {
                    this.log(false, String.format("Приз - %s", item));
                    return item;
                }
            }
        }
    }

    /**
     * Добавление/Редактирование
     *
     * @param id     Идентификатор
     * @param name   Название игрушки
     * @param chance Шанс выпадения
     * @return
     */
    public Toy add(int id, String name, double chance) {
        boolean isUpdate = false;
        Toy addToy = new Toy(this, id, name, chance);
        if (name.isEmpty()) this.err(addToy, "Не указано название");
        if (chance < 0.0001 || chance > 1) this.err(addToy, "Требуется значение шанса от 0.0001 до 1");
        if (this.ids.contains(id)) {
            isUpdate = true;
            this.remove(id);
        }
        this.ids.add(id);
        this.data.add(addToy);
        this.allChances += addToy.getChance();
        this.log(!isUpdate ? "add" : "update", addToy.toString());
        return addToy;
    }

    /**
     * Удаление
     *
     * @param id Идентификатор
     * @return
     */
    public Toy remove(int id) {
        String log = null;
        Toy removeToy = null;
        for (Toy item : this.data) {
            if (item.getId() == id) {
                removeToy = item;
                log = item.toString();
                break;
            }
        }
        if (removeToy != null) {
            this.allChances -= removeToy.getChance();
            this.data.remove(removeToy);
        }
        if (!this.ids.contains(id)) this.err("Запись с таким id(" + id + ") не существует");
        else {
            this.ids.remove((Integer) id);
            this.log("remove", log);
        }
        return removeToy;
    }

    // *** Service ***
    private double checkChance(double chance) {
        return Double.parseDouble(String.format("%.4f", chance).replace(',', '.'));
    }

    private void log(String action, String toy) {
        this.log(false, String.format(
                "%s [%s]",
                switch (action) {
                    case "add" -> "Добавление";
                    case "update" -> "Редактирование";
                    case "remove" -> "Удаление";
                    default -> "Undefined";
                },
                toy
        ));
    }

    private void log(Object message) {
        this.log(false, message.toString());
    }

    private void err(Toy object, String message) {
        this.log(true, String.format("Ошибка добавления [%s]\n%s", object, message));
    }

    private void err(String message) {
        this.log(true, String.format("Ошибка удаления\n%s", message));
    }

    // *** Logs & errors ***
    private void log(boolean isError, String message) {
        this.logs.add(new Log(isError, message));
        // Console.out.color(isError ? "r" : "b").log(message);
        // if (isError) throw new ToysException(message);
    }
}
