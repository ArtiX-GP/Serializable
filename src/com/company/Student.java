package com.company;

public class Student implements Serializable {
    private String name;
    private String surname;
    private int age;

    public Student() {
    }

    public Student(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + " ";
    }

    @Override
    public String toStorableString(Object object) {
        // ((Student) object) - это каст, то есть приведение общего класса object к классу Student.
        // На самом деле я не совсем понимаю зачем здесь параметр object, если метод вызывается из конкретного экземпляра класса
        // В любом случае, если мы передаем сюда какой-то объект, то мы ожидаем, что это будет объект типа Student
        // а в файле Teacher в этом же методе ожидаем объект типа Teacher - логично
        // так вот, если приведение отбросить, то программа не поймет какой конкретно объект мы ожидаем, и не даст доступа к полям
        // name, surname и age
        // Если передать сюда другой объект, то программа вылетит, так как никакой обратки ощибок здесь нет, но ее наличие не требовалось
        return "Student" + "###" + ((Student) object).name + "###" + ((Student) object).surname + "###" + ((Student) object).age;
    }

    @Override
    public Object fromStorableString(String string) {
        // Получаем из строки параметры в том порядке, в котором записывали в методе toStorableString();
        // ### - это разделитель, можно изменить на любой другой (тогда надо будет поправить метод toStorableString()).
        String[] args = string.split("###");

        // Создаем нового студента, и возвращаем созданный экземпляр.
        // Запись можно было упростить, если ты знаешь как, то изменишь, а если нет, то оставь так, чтобы не было вопросов.
        this.name = args[1];
        this.surname = args[2];
        this.age = Integer.parseInt(args[3]);
        return this;
    }
}
