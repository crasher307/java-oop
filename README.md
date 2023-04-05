### Объектно-ориентированное программирование (семинары)
<details class="desc"><summary>Доп. инфа</summary>

[pom.xml](https://github.com/crasher307/java-oop/blob/master/pom.xml)

</details>
<details class="desc"><summary>Урок 1. Принципы ООП: Инкапсуляция, наследование, полиморфизм</summary>

~~[Работа во время семинара](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson1/work)~~\
[Домашнее задание](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson1/homework)
```java
// Создать класс Товар, имеющий переменные имя, цена, рейтинг.
class Product {
    String name;
    double price;
    double rating;
}
// Создать класс Категория, имеющий переменные имя и массив товаров. Создать несколько объектов класса Категория.
class Category {
    String name;
    ArrayList<Product> products; // товары
}
// Создать класс Basket, содержащий массив купленных товаров.
class Basket {
    ArrayList<Product> products; // товары в корзине
    
    void add(Product product); // добавить товар в корзину
    void remove(Product product); // удалить товар из корзины
}
// Создать класс User, содержащий логин, пароль и объект класса Basket. Создать несколько объектов класса User.
class User {
    String login;
    String password;
    Basket basket = new Basket(); // корзина товаров
}
// Вывести на консоль каталог продуктов. (все продукты магазина)
// Вывести на консоль покупки посетителей магазина. (После покупки у пользователя добавляется товар, а из магазина - удаляется)
```

</details>
<details class="desc"><summary>Урок 2. Принципы ООП: Абстракция и интерфейсы. Пример проектирования</summary>

~~[Работа во время семинара](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson2/work)~~\
[Домашнее задание](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson2/homework)
```java
// Создайте три класса: Человек, Кот, Робот, которые наследуются от одного класса.
// Эти классы должны уметь бегать и прыгать, все также с выводом информации о действии в консоль.
abstract class Player {
    double maxRun; // Макс. дальность бега
    double maxJump; // Макс. высота прыжка
    
    void run();
    void jump();
}
class People extends Player {}
class Cat extends Player {}
class Robot extends Player {}
// Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
// У препятствий есть длина (для дорожки) или высота (для стены),а у участников ограничения на бег и прыжки.
abstract class Obstacle {
    int value; // размер препятсвия (длина/высота)
}
class Track extends Obstacle {}
class Wall extends Obstacle {}
// Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
// Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.
class Main {
    ArrayList<Player> players = new ArrayList<>(List.of(
            new People(),
            new Cat(),
            new Robot()
    ));
    ArrayList<Obstacle> obstacles = new ArrayList<>(List.of(
            new Track(),
            new Wall()
    ));
}
```

</details>
<details class="desc"><summary>Урок 3. Некоторые стандартные интерфейсы Java и примеры их использования</summary>

[Работа во время семинара](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson3/work)\
[Домашнее задание](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson3/homework)
```text
1. Доделать вариации с игрой на английском/русском языке, сигнатуры уже приведены в прикрепленном файле
2. Улучшить интерфейсную часть игры
3. * создать историю ходов и по окончании игры вывести её, в зависимости от ответа пользователя (y-вывести, n -выводить не следует)
```

</details>
<details class="desc"><summary>Урок 4. ООП: Обобщения. ч1</summary>

~~[Работа во время семинара](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson4/work)~~\
[Домашнее задание](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson4/homework)
```text
1. Расширить класс калькулятор на умножение
2. Расширить класс калькулятор на деление
3. Расширить класс калькулятор на бинарный перевод(принимаемые значения как стринг, так и инт - тут необходимо подумать как наилучшим образом реализовать, что будет если будут приниматься округляемые Double/Float (округляемые -> с нулем на конце прим. 3.0 , 4.0 и тд т))
```

</details>
<details class="desc"><summary>Урок 5. От простого к практике</summary>

~~[Работа во время семинара](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson5/work)~~\
[Домашнее задание](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson5/homework)
```text
Задание
1. Создать класс УчебнаяГруппа содержащая в себе поля Преподаватель и список Студентов
2. Создать класс УчебнаяГруппаСервис, в котором реализована функция(входные параметры - (Teacher, List<Strudent>)) формирования из Студентов и Преподавателя УчебнойГруппы и возвращения его
3. Создать метод в Контроллере, в котором агрегируются функции получения списка студентов (их id) и преподавателя (его id) и формирования учебной группы, путем вызова метода из сервиса
4. Все вышеуказанное создать согласно принципам ООП пройдённым на семинаре
Формат сдачи: ссылка на гитхаб проект
```
- [класс УчебнаяГруппа](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson5/homework/model/ModelGroup.java)
- [класс УчебнаяГруппаСервис](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson5/homework/controller/ServiceGroup.java)
- [метод в Контроллере](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson5/homework/controller/Group.java) - `create(int number, Teacher teacher, List<Student> students)`

</details>
<details class="desc"><summary>Урок 6. ООП Дизайн и Solid</summary>

~~[Работа во время семинара](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson6/work)~~\
[Домашнее задание](https://github.com/crasher307/java-oop/tree/master/src/main/java/lesson6/homework)
```text
Дз на закрепление:
Взять реализованный код в рамках последнего семинара (5) и продемонстрировать применение принципов, усвоенных на семинаре.
Нужно в проекте прокомментировать участки кода, которые рефакторим, какой принцип применяем и почему
```

</details>
<details class="desc"><summary>Стили для IDE</summary>

<style>
.desc {
    margin: 0 0 0 1em;
    padding: 0 0 1em;
}
.desc summary {
    margin: 0 0 -1em;
    list-style-position: outside;
    cursor: pointer;
    
}
.desc pre {
    border: 1px solid #37b;
    margin: -1em 0 1.5em;
    padding: 0.3em 0.6em;
}
</style>

</details>