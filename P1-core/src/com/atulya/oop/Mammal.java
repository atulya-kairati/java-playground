package com.atulya.oop;

import java.util.Objects;

public class Mammal {

    static {
        System.out.println("Static block initializer");
    }

    private String name;
    private int lifeSpan;
    private boolean endangered;

    {
        System.out.println("Instance block initializer");
    }

    public Mammal(String name, int lifeSpan){
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.endangered = false;
    }

    public Mammal(String name, int lifeSpan, boolean endangered){
        this(name, lifeSpan);
        this.endangered = endangered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public boolean isEndangered() {
        return endangered;
    }

    public void setEndangered(boolean endangered) {
        this.endangered = endangered;
    }

    @Override
    public String toString() {
        return "Mammal{" +
                "name='" + name + '\'' +
                ", lifeSpan=" + lifeSpan +
                ", endangered=" + endangered +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mammal mammal = (Mammal) o;
        return lifeSpan == mammal.lifeSpan &&
                endangered == mammal.endangered &&
                Objects.equals(name, mammal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lifeSpan, endangered);
    }
}
