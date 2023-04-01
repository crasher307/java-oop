package lesson5.homework.model;

import lesson5.homework.controller.*;
import lombok.*;

@Getter
public
class ModelStudent extends AbstractUser implements Student {
    public static int ids = 0;
    private final int id = ids++;
    private Group group = null;

    public ModelStudent(String lastName, String firstName, String patronymic) {
        super(lastName, firstName, patronymic);
    }

    // --- Работа с группой --------------------------
    public Student setGroup(Group group) {
        this.changeGroup(this.group, group);
        return this;
    }

    public Student removeGroup() {
        this.changeGroup(this.group, null);
        return this;
    }

    // --- private ---
    private void changeGroup(Group oldGroup, Group newGroup) {
        this.group = newGroup;
        if (oldGroup != null && oldGroup.getStudents().contains(this)) {
            oldGroup.removeStudent(this); // Удаляем из старой группы
        }
        if (newGroup != null && !newGroup.getStudents().contains(this)) {
            newGroup.addStudent(this); // Добавляем в новую группу
        }
    }

    // -----------------------------------------------
    public Student clear() {
        this.group.removeStudent(this);
        return this;
    }

    @Override
    public String toString() {
        return String.format(
                "%s.id_%d [lastName: %s, firstName: %s, patronymic: %s, group: %s]",
                this.getType(),
                this.getId(),
                this.getLastName(),
                this.getFirstName(),
                this.getPatronymic(),
                this.group == null ? null : String.format("Group.id_%d", this.group.getId())
        );
    }
}
