package lesson5.homework.model;

import lesson5.homework.controller.*;
import lombok.*;

@Getter
abstract class AbstractUser implements User {
    private final String type = this.getClass().getSimpleName();
    private final String lastName;
    private final String firstName;
    private final String patronymic;

    public AbstractUser(String lastName, String firstName, String patronymic) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    public String getFullName() {
        return String.format("%s %s %s", this.lastName, this.firstName, this.patronymic);
    }

    @Override
    public String toString() {
        return String.format("%s [lastName: %s, firstName: %s, patronymic: %s]", this.type, this.lastName, this.firstName, this.patronymic);
    }
}
