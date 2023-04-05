package lesson6.homework;

import lombok.Getter;

@Getter
abstract class ModelUser implements User {
    // S: single responsibility principle; Единственная ответственность
    private static Integer length = 0;
    protected final Integer id = length++; // O: open-closed principle; Изменения в код, использующий функционал вноситься не будут
    protected final String lastName;
    protected final String firstName;
    protected final String patronymic;

    public ModelUser(String lastName, String firstName, String patronymic) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public String getFullName() {
        return String.format("%s %s %s", this.lastName, this.firstName, this.patronymic);
    }

    protected abstract String info(); // D: inversion principle; зависимость на абстракции

    @Override
    public String toString() {
        return String.format("%s{%s}", this.getClass().getSimpleName(), this.info());
    }
}
