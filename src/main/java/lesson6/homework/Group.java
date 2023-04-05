package lesson6.homework;

import java.util.List;

interface Group {
    Integer getId();

    Integer getTeacherId();

    List<Integer> getStudentIds();

    void changeTeacher(int teacherId);

    void removeTeacher();

    void addStudent(int studentId);

    void removeStudent(int studentId);

    void removeStudents();
}
