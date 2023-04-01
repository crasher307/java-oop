package lesson5.homework;

import lesson5.homework.controller.*;
import lib.Console;

import java.util.List;

public class Main {
    private static final Console console = Console.out;

    public static void main(String[] args) {
        // Задание
        // 1. Создать класс УчебнаяГруппа содержащая в себе поля Преподаватель и список Студентов
        // - controller/Group
        // 2. Создать класс УчебнаяГруппаСервис, в котором реализована функция(входные параметры - (Teacher, List<Strudent>)) формирования из Студентов и Преподавателя УчебнойГруппы и возвращения его
        // controller/ServiceGroup
        // 3. Создать метод в Контроллере, в котором агрегируются функции получения списка студентов (их id) и преподавателя (его id) и формирования учебной группы, путем вызова метода из сервиса
        // controller/Group.create(int number, Teacher teacher, List<Student> students)
        // 4. Все вышеуказанное создать согласно принципам ООП пройдённым на семинаре
        // Формат сдачи: ссылка на гитхаб проект

        Group.create(
                101,
                Teacher.create("Иванов", "Иван", "Иванович"),
                List.of(
                        Student.create("Петров", "Иван", "Петрович"),
                        Student.create("Петров", "Иван", "Сидорович"),
                        Student.create("Петров", "Иван", "Николаевич"),
                        Student.create("Петров", "Иван", "Никитьевич"),
                        Student.create("Петров", "Иван", "Олегович")
                )
        );

        Group.viewAll();
        Teacher.viewAll();
        Student.viewAll();
        // test();
    }

    private static void test() {
        Group group1, group2;
        Teacher teacher;
        Student student;
        group1 = Group.create(101)
                .setTeacher(teacher = Teacher.create("Преподаватель 1", null, null))
                .addStudent(student = Student.create("Студент 1", null, null))
                .addStudent(Student.create("Студент 2", null, null))
                .addStudent(Student.create("Студент 3", null, null))
                .addStudent(Student.create("Студент 4", null, null))
                .addStudent(Student.create("Студент 5", null, null));

        group2 = Group.create(102);
        group2.setTeacher(Teacher.create("Преподаватель 2", null, null));
        group2.addStudent(student);

        teacher.addGroup(group2);

        // Тест корректности отработки
        // group1
        //         .getTeacher()
        //         .getGroups().get(0)
        //         .getStudents().get(1)
        //         .setGroup(group2).getGroup()
        //         .removeTeacher();


        console.color("r").log("Список групп:");
        for (Group itemG : Group.DATA) console.color("y").log("\t%s\n", itemG);
        console.color("r").log("Список преподавателей:");
        for (User itemT : Teacher.DATA) console.color("g").log("\t%s\n", itemT);
        console.color("r").log("Список студентов:");
        for (User itemS : Student.DATA) console.color("b").log("\t%s\n", itemS);


        // for (Group itemG : Group.DATA) {
        //     console.color("r").log(itemG);
        //     console.color("r").log("Группа № %d\n", itemG.getNumber());
        //     console.color("g").log("\tПреподаватель:");
        //     console.color("b").log("\t\t%s\n", itemG.getTeacher());
        //     console.color("g").log("\tСписок студентов:");
        //     for (Student itemS : itemG.getStudents()) console.color("b").log("\t\t%s\n", itemS);
        // }
    }
}

