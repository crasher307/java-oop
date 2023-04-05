package lesson6.homework;

import java.util.List;

interface Teacher extends User {
    // I: interface segregation principle; Плодим интерфейсы
    List<Integer> getGroups();

    void addGroup(int groupId);

    void removeGroup(int groupId);

    void removeGroups();
}
