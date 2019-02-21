package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DB {
    private ArrayList<Serializable> objects;

    public DB(){
        objects = new ArrayList<>();
    }

    public void add(Serializable object){
        objects.add(object);
    }

    public Serializable get(int index){
        return objects.get(index);
    }

    public void toFile(String path){
        // Для записи будем юзать FileWriter, так как у нас простые строки, и их нет смысла записывать побайтово
        // Для каких-нибудь бинарных данных заюзали бы FileOutputStream
        try {
            FileWriter fileWriter = new FileWriter(path);
            String allData = "";
            for (int i = 0; i < objects.size(); i++) {
                allData = allData + objects.get(i).toStorableString(objects.get(i));
                allData = allData + "\n"; // Перенос на новую строку.
            }
            fileWriter.write(allData);
            // Очистить кэш ОЗУ, записать в файл.
            fileWriter.flush();

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Не удалось записать в файл: " + e.getMessage());
        }
    }

    public void fromFile(String path){
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.contains("Student")) {
                    // Ненадежный способ проверки, так как если преподу дать имя Student, то может заглючить
                    // Скажи ему, что вероятность такого имени у преподавателя мала, поэтому сойдет)
                    // Если докопается, то напишешь, переделаем.
                    objects.add((Student) new Student().fromStorableString(line));
                } else {
                    objects.add((Teacher) new Teacher().fromStorableString(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        DB db = new DB();
        db.add(new Student("John", "Smith", 19));
        db.add(new Student("Dana", "Break", 21));
        db.add(new Teacher("Simon", "Born", 44, "Philosophy"));
        db.toFile("test.db");

        DB restored = new DB();
        restored.fromFile("test.db");
        System.out.println((Student)restored.get(0));

    }

}