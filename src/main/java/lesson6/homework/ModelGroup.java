package lesson6.homework;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
final class ModelGroup implements Group {
    private final Integer id;
    private Integer teacherId;
    private final List<Integer> studentIds;

    public ModelGroup(int groupId) {
        this(groupId, 0, new ArrayList<>());
    }

    public ModelGroup(int groupId, int teacherId) {
        this(groupId, teacherId, new ArrayList<>());
    }

    public ModelGroup(int groupId, List<Integer> studentIds) {
        this(groupId, 0, studentIds);
    }

    public ModelGroup(int groupId, int teacherId, List<Integer> studentIds) {
        this.id = groupId;
        this.teacherId = teacherId;
        this.studentIds = studentIds;
    }

    public void changeTeacher(int teacherId) {
        this.teacherId = teacherId;
    }

    public void removeTeacher() {
        this.teacherId = 0;
    }

    public void addStudent(int studentId) {
        this.studentIds.add(studentId);
    }

    public void removeStudent(int studentId) {
        this.studentIds.remove((Integer) studentId);
    }

    public void removeStudents() {
        this.studentIds.clear();
    }

    @Override
    public String toString() {
        return String.format("ModelGroup{id=\"%s\", teacherId=\"%s\", studentIds=%s}", this.id, this.teacherId, this.studentIds);
    }
}
