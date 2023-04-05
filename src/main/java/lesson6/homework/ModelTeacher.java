package lesson6.homework;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
final class ModelTeacher extends ModelUser implements Teacher {
    // L: Liskov substitution principle; Расширяем базовый (абстрактный) класс
    private final List<Integer> groups;

    public ModelTeacher(String lastName, String firstName, String patronymic) {
        this(lastName, firstName, patronymic, new ArrayList<>());
    }

    public ModelTeacher(String lastName, String firstName, String patronymic, List<Integer> groups) {
        super(lastName, firstName, patronymic);
        this.groups = groups;
    }

    public void addGroup(int groupId) {
        if (!this.groups.contains(groupId)) this.groups.add(groupId);
    }

    public void removeGroup(int groupId) {
        if (this.groups.contains(groupId)) this.groups.remove((Integer) groupId);
    }

    public void removeGroups() {
        this.groups.clear();
    }

    @Override
    protected String info() {
        return String.format("id=\"%s\", lastName=\"%s\", firstName=\"%s\", patronymic=\"%s\", groups=%s", this.id, this.lastName, this.firstName, this.patronymic, this.groups);
    }
}
