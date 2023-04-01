package lesson5.homework.controller;

import lesson5.homework.model.ModelStudent;
import lesson5.homework.view.View;

import java.util.ArrayList;
import java.util.List;

public interface Student extends User {
    List<Student> DATA = new ArrayList<>();

    static Student create(String lastName, String firstName, String patronymic) {
        Student student = new ModelStudent(lastName, firstName, patronymic);
        DATA.add(student);
        return student;
    }

    // TODO List<Student> search(id|groupNumber|lastName|firstName|patronymic)

    static void remove(Student student) {
        DATA.remove(student.clear());
    }

    static void viewAll() {
        View.studentList(DATA);
    }

    /** ModelData */
    Group getGroup(); // Getter

    Student setGroup(Group group);

    Student removeGroup();

    Student clear();
}
