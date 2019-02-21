package com.company;

public class Teacher implements Serializable {
    private String name;
    private String surname;
    private int age;
    private String departemnt;

    public Teacher(String name, String surname, int age, String departemnt) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.departemnt = departemnt;
    }

    public Teacher() {
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname + " " + this.departemnt;
    }

    @Override
    public String toStorableString(Object object) {
        // Этот момент я объяснял в файле Student, ничем собсна не отличается.
        return "Teacher" + "###" + ((Teacher) object).name + "###" + ((Teacher) object).surname + "###" + ((Teacher) object).age
                + "###" + ((Teacher) object).departemnt;
    }

    @Override
    public Object fromStorableString(String string) {
        // Всё то же самое, что и в Student, смотри комменты там.
        String[] args = string.split("###");

        this.name = args[1];
        this.surname = args[2];
        this.age = Integer.parseInt(args[3]);
        this.departemnt = args[4];
        return this;
    }
}
