package lesson5.homework.view;

import lesson5.homework.controller.*;
import lib.Console;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

public class View {
    @NoArgsConstructor
    private static final class Table {
        private static class Field {
            private final String value;
            private final int size;

            public Field(Object value) {
                this(value, 0);
            }

            public Field(Object value, int size) {
                this.value = String.valueOf(value).replace(" ", "#");
                this.size = size;
            }
        }

        private final List<Field> header = new ArrayList<>();
        private final List<List<Field>> data = new ArrayList<>();

        private String get() {
            StringBuilder response = new StringBuilder();
            StringBuilder d;
            int length = 3;
            for (Field field : this.header) {
                response.append(
                        String.format("-%" + field.size + "s-", String.format("#%s#", field.value))
                                .replace(' ', '-')
                                .replace('#', ' ')
                );
            }
            response.append("\n");
            for (List<Field> item : this.data) {
                d = new StringBuilder();
                for (int i = 0; i < item.size(); i++)
                    d.append(
                            String.format("[ %" + (this.header.get(i).size - 2) + "s ]", item.get(i).value)
                                    .replace('#', ' ')
                    );
                length = d.length();
                d.append("\n");
                response.append(d);
            }
            response.append(String.format("%" + length + "s", "").replace(' ', '-'));
            return response.toString();
        }
    }

    public static void groupList(List<Group> groups) {
        Table table = new Table();
        table.header.addAll(List.of(
                new Table.Field("ID", 6),
                new Table.Field("Номер", 10),
                new Table.Field("Преподаватель", 40)
        ));
        for (Group group : groups) {
            table.data.add(List.of(
                    new Table.Field(group.getId()),
                    new Table.Field(group.getNumber()),
                    new Table.Field(group.getTeacher() == null ? "Отсутствует" : group.getTeacher().getFullName())
            ));
        }
        Console.out.color("c").log("Список учебных групп:");
        Console.out.color("w").log("%s\n", table.get());
    }

    public static void teacherList(List<Teacher> teachers) {
        Table table = new Table();
        table.header.addAll(List.of(
                new Table.Field("ID", 6),
                new Table.Field("Фамилия", 20),
                new Table.Field("Имя", 20),
                new Table.Field("Отчество", 20),
                new Table.Field("Курируемые группы", 30)
        ));
        List<Integer> groups;
        String groupsView;
        for (Teacher teacher : teachers) {
            groups = new ArrayList<>();
            for (Group group : teacher.getGroups()) groups.add(group.getNumber());
            groupsView = groups.toString();
            groupsView = groupsView.substring(1, groupsView.length() - 1);
            table.data.add(List.of(
                    new Table.Field(teacher.getId()),
                    new Table.Field(teacher.getLastName()),
                    new Table.Field(teacher.getFirstName()),
                    new Table.Field(teacher.getPatronymic()),
                    new Table.Field(groupsView.isEmpty() ? "Не курирует" : groupsView)
            ));
        }
        Console.out.color("c").log("Список преподавателей:");
        Console.out.color("w").log("%s\n", table.get());
    }

    public static void studentList(List<Student> students) {
        Table table = new Table();
        table.header.addAll(List.of(
                new Table.Field("ID", 6),
                new Table.Field("Фамилия", 20),
                new Table.Field("Имя", 20),
                new Table.Field("Отчество", 20),
                new Table.Field("Группа", 10)
        ));
        for (Student student : students) {
            table.data.add(List.of(
                    new Table.Field(student.getId()),
                    new Table.Field(student.getLastName()),
                    new Table.Field(student.getFirstName()),
                    new Table.Field(student.getPatronymic()),
                    new Table.Field(student.getGroup() == null ? "Отсутствует" : student.getGroup().getNumber())
            ));
        }
        Console.out.color("c").log("Список студентов:");
        Console.out.color("w").log("%s\n", table.get());
    }
}
