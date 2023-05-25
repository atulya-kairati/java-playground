package com.atulya.oop;

public class Creator {
    public static void main(String[] args) {
        Mammal man = new Mammal("Homo Sapiens", 80);
        Mammal tiger = new Mammal("Panthera Tigris", 15, true);

        System.out.println(man);
        System.out.println(tiger);
    }
}
