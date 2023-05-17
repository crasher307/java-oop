package controlWork.view;

import controlWork.controller.Toys;
import controlWork.model.Toy;
import com.opencsv.*;
import lib.Console;

import java.io.FileReader;
import java.io.FileWriter;

public class Lottery {
    private final String dir = "src/main/java/controlWork/";
    private final Toys toys = new Toys();

    public void start() {
        this.load();
        Toy prize = this.toys.getLot();
        this.save(prize);
        Console.out.color("b").log("Приз - %s\n", prize);
    }

    public void test10() {
        for (int i = 0; i < 10; i++) this.start();
    }

    /** Загрузка данных для лотереи */
    public void load() {
        try (CSVReader reader = new CSVReader(new FileReader(dir + "prizes.csv"))) {
            String[] fields;
            for (String[] s : reader.readAll()) {
                for (String s1 : s) {
                    fields = s1.split(";");
                    this.toys.add(Integer.parseInt(fields[0]), fields[1], Double.parseDouble(fields[2]));
                }
            }
        } catch (Exception e) {
            Console.out.err(e);
        }
    }

    /** Сохранение данных (остаток + победители) */
    public void save(Toy prize) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(dir + "winners.csv", true))) {
            String[] record = {Integer.toString(prize.getId()), prize.getName(), Double.toString(prize.getFullChance())};
            writer.writeNext(record, false);
        } catch (Exception e) {
            Console.out.err(e);
        }
    }
}
