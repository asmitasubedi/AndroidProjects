package com.example.asmitas.workshopclass3.data;

/**
 * Created by AsmitaS on 7/8/2017.
 */
public class Student {
    private int id;
    private String name;
    private int roll;

    public Student(int id, String name, int roll){
        this.id = id;
        this.name = name;
        this.roll = roll;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

}
