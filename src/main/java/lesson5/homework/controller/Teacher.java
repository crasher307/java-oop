package lesson5.homework.controller;

import lesson5.homework.model.ModelTeacher;
import lesson5.homework.view.View;

import java.util.ArrayList;
import java.util.List;

public interface Teacher extends User {
    List<Teacher> DATA = new ArrayList<>();

    static Teacher create(String lastName, String firstName, String patronymic) {
        Teacher teacher = new ModelTeacher(lastName, firstName, patronymic);
        DATA.add(teacher);
        return teacher;
    }

    // TODO List<Teacher> search(id|groupNumber|lastName|firstName|patronymic)

    static void remove(Teacher teacher) {
        DATA.remove(teacher.clear());
    }

    static void viewAll() {
        View.teacherList(DATA);
    }

    /** ModelData */
    List<Group> getGroups(); // Getter

    Teacher addGroup(Group group);

    Teacher removeGroup(Group group);

    Teacher clear();
}
