package com.atulya.oop;

public class Mammal {
    private String name;
    private int lifeSpan;
    private boolean endangered;

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
}
