package lesson5.homework.controller;

import java.util.List;

public class ServiceGroup {
    public static Group create(Group group, Teacher teacher, List<Student> students) {
        group.setTeacher(teacher);
        for (Student student : students) student.setGroup(group);
        return group;
    }
}
