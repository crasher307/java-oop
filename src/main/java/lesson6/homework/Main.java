package lesson6.homework;

import lib.Console;

import java.util.List;

// Дз на закрепление:
// Взять реализованный код в рамках последнего семинара (5) и продемонстрировать применение принципов, усвоенных на семинаре.
// Нужно в проекте прокомментировать участки кода, которые рефакторим, какой принцип применяем и почему
public class Main {
    private static final Console console = Console.out;

    public static void main(String[] args) {
        // Применены принципы:
        // S: Принцип единственной ответственности (single responsibility principle)
        // Для каждого класса должно быть определено единственное назначение. Все ресурсы, необходимые для его осуществления, должны быть инкапсулированы в этот класс и подчинены только этой задаче.
        // O: Принцип открытости/закрытости (open-closed principle)
        // «программные сущности ... должны быть открыты для расширения, но закрыты для модификации».
        // L: Принцип подстановки Лисков (Liskov substitution principle)
        // «функции, которые используют базовый тип, должны иметь возможность использовать подтипы базового типа не зная об этом». См. также контрактное программирование.
        // I: Принцип разделения интерфейса (interface segregation principle)
        // «много интерфейсов, специально предназначенных для клиентов, лучше, чем один интерфейс общего назначения».
        // D: Принцип инверсии зависимостей (dependency inversion principle)
        // «Зависимость на Абстракциях. Нет зависимости на что-то конкретное».

        // Потому что это позволит в дальнейшем расширять функционал

        Controller<Group> groups = new Controller<>();
        Controller<User> users = new Controller<>();

        groups.add(new ModelGroup(
                101,
                users.add(new ModelTeacher("Преподаватель 1", null, null)).getId(),
                List.of(
                        users.add(new ModelStudent("Студент 1", null, null)).getId(),
                        users.add(new ModelStudent("Студент 2", null, null)).getId(),
                        users.add(new ModelStudent("Студент 3", null, null)).getId()
                )
        ));

        groups.add(new ModelGroup(
                102,
                users.add(new ModelTeacher("Преподаватель 2", null, null)).getId(),
                List.of(
                        users.add(new ModelStudent("Студент 4", null, null)).getId(),
                        users.add(new ModelStudent("Студент 5", null, null)).getId(),
                        users.add(new ModelStudent("Студент 6", null, null)).getId()
                )
        ));

        // TODO требует доработки (прим. дз 5 ур.)
        console.log(users);
        console.log(groups);
    }
}
/** Views */
// TODO