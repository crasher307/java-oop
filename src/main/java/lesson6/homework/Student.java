package lesson6.homework;

interface Student extends User {
    // I: interface segregation principle; Плодим интерфейсы
    Integer getGroup();

    void setGroup(int groupId);

    void removeGroup();
}
