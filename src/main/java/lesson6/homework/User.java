package lesson6.homework;

/** Interfaces */
interface User {
    // I: interface segregation principle; Добавляем интерфейсы для работы
    Integer getId();

    String getLastName();

    String getFirstName();

    String getPatronymic();

    String getFullName();
}
