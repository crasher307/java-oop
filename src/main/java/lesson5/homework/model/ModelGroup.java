package lesson5.homework.model;

import lesson5.homework.controller.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public
class ModelGroup implements Group {
    public static int ids = 0;
    private final int id = ids++;
    private final int number;
    private Teacher teacher = null;
    private final List<Student> students = new ArrayList<>();

    public ModelGroup(int number) {
        this.number = number;
    }

    // --- Работа с преподавателем -------------------
    public Group setTeacher(Teacher teacher) {
        this.changeTeacher(this.teacher, teacher);
        return this;
    }

    public Group removeTeacher() {
        this.changeTeacher(this.teacher, null);
        return this;
    }

    // --- private ---
    private void changeTeacher(Teacher oldTeacher, Teacher newTeacher) {
        this.teacher = newTeacher;
        if (oldTeacher != null && oldTeacher.getGroups().contains(this)) {
            oldTeacher.removeGroup(this); // Удаляем у старого преподавателя
        }
        if (newTeacher != null && !newTeacher.getGroups().contains(this)) {
            newTeacher.addGroup(this); // Добавляем новому преподавателю
        }
    }

    // --- Работа со студентами ----------------------
    public Group addStudent(Student student) {
        if (student != null) {
            if (!this.students.contains(student)) this.students.add(student); // Добавляем студента
            if (student.getGroup() != this) student.setGroup(this); // Добавляем группу студенту
        }
        return this;
    }

    public Group removeStudent(Student student) {
        if (student != null) {
            this.students.remove(student); // Удаляем студента
            if (student.getGroup() == this) student.removeGroup(); // Удаляем группу студенту
        }
        return this;
    }

    // -----------------------------------------------
    public Group clear() {
        this.teacher.removeGroup(this);
        for (Student student : this.students) student.removeGroup();
        return this;
    }

    @Override
    public String toString() {
        List<String> studentsObj = new ArrayList<>();
        for (Student student : this.students) studentsObj.add(String.format("Student.id_%d", student.getId()));
        return String.format(
                "Group.id_%s [number: %s, teacher: %s, students: %s]",
                this.id,
                this.number,
                this.teacher == null ? null : String.format("Teacher.id_%d", this.teacher.getId()),
                studentsObj
        );
    }
}
