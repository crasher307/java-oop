package lesson6.homework;

import lombok.Getter;

@Getter
final class ModelStudent extends ModelUser implements Student {
    // L: Liskov substitution principle; Расширяем базовый (абстрактный) класс
    private Integer group;

    public ModelStudent(String lastName, String firstName, String patronymic) {
        this(lastName, firstName, patronymic, 0);
    }

    public ModelStudent(String lastName, String firstName, String patronymic, int groupId) {
        super(lastName, firstName, patronymic);
        this.group = groupId;
    }

    public void setGroup(int groupId) {
        this.group = groupId;
    }

    public void removeGroup() {
        this.group = 0;
    }

    @Override
    protected String info() {
        return String.format("id=\"%s\", lastName=\"%s\", firstName=\"%s\", patronymic=\"%s\", group=\"%s\"", this.id, this.lastName, this.firstName, this.patronymic, this.group);
    }
}
