package org.torquemada.sample;

/**
 * Created by torquemada on 3/3/16.
 */
public class Person {

    private String name;
    private int age;

    public Person(String вася, int i) {
        name = вася;
        age = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
