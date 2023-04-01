package lesson5.homework.controller;

import lesson5.homework.model.ModelGroup;
import lesson5.homework.view.View;

import java.util.ArrayList;
import java.util.List;

public interface Group {
    List<Group> DATA = new ArrayList<>();

    static Group create(int number) {
        Group group = new ModelGroup(number);
        DATA.add(group);
        return group;
    }

    static Group create(int number, Teacher teacher, List<Student> students) {
        Group group = create(number);
        return ServiceGroup.create(group, teacher, students);
    }

    // TODO List<Student> search(id|groupNumber|lastName|firstName|patronymic)

    static Group getByNumber(int number) {
        for (Group group : DATA) {
            if (group.getNumber() == number) return group;
        }
        return null;
    }

    static void remove(Group group) {
        DATA.remove(group.clear());
    }

    static void viewAll() {
        View.groupList(DATA);
    }

    /** ModelData */
    int getId(); // Getter

    int getNumber(); // Getter

    Teacher getTeacher(); // Getter

    Group setTeacher(Teacher teacher);

    Group removeTeacher();

    List<Student> getStudents(); // Getter

    Group addStudent(Student student);

    Group removeStudent(Student student);

    Group clear();
}
