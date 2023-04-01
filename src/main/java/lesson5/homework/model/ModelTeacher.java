package lesson5.homework.model;

import lesson5.homework.controller.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public
class ModelTeacher extends AbstractUser implements Teacher {
    public static int ids = 0;
    private final int id = ids++;
    private final List<Group> groups = new ArrayList<>();

    public ModelTeacher(String lastName, String firstName, String patronymic) {
        super(lastName, firstName, patronymic);
    }

    // --- Работа с группами -------------------------
    public Teacher addGroup(Group group) {
        if (group != null) {
            if (!this.groups.contains(group)) this.groups.add(group); // Добавляем группу
            if (group.getTeacher() != this) group.setTeacher(this); // Добавляем куратора
        }
        return this;
    }

    public Teacher removeGroup(Group group) {
        if (group != null) {
            this.groups.remove(group); // Удаляем группу
            if (group.getTeacher() == this) group.removeTeacher(); // Удаляем куратора
        }
        return this;
    }

    // -----------------------------------------------
    public Teacher clear() {
        for (Group group : this.groups) group.removeTeacher();
        return this;
    }

    @Override
    public String toString() {
        List<String> groupsObj = new ArrayList<>();
        for (Group group : this.groups) groupsObj.add(String.format("Group.id_%d", group.getId()));
        return String.format(
                "%s.id_%d [lastName: %s, firstName: %s, patronymic: %s, groups: %s]",
                this.getType(),
                this.getId(),
                this.getLastName(),
                this.getFirstName(),
                this.getPatronymic(),
                groupsObj
        );
    }
}
