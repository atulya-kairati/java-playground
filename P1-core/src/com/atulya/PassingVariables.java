package com.atulya;

import java.text.MessageFormat;

class Vector2 {
    double x, y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return MessageFormat.format("({0}, {1})", x, y);
    }

}

public class PassingVariables {
    public static void main(String[] args) {

        // Primitives
        double t = 5;
        double s = t; // s gets a copy of value of t

        System.out.println("s == t: " + (s == t));

        s -= 2; // this will only mutate s but not t
        System.out.println("s == t: " + (s == t));

        // similarly when we pass a primitive to a method
        // the method gets a copy, not the original primitive
        double mutatedS = mutatePrimitive(s);
        System.out.println("mutatedS == s: " + (mutatedS == s));

        // Objects

        // v stores a reference to the Vector2 obj in the heap
        Vector2 v = new Vector2(2, 4);
        System.out.println("v = " + v);

        // here v2 gets a copy of the reference value held by v
        // so v2 and v refer to the same object on the heap
        Vector2 v2 = v;

        // any changes to v2 will reflect on v and vice versa
        v2.x = 5;
        System.out.println("v = " + v);
        System.out.println("v2 = " + v2);

        // similarly when we pass a object to a method
        // we are passing a copy of the reference store in the variable
        // So we are passing a value but the method's param will refer to the
        // same object in memory, so any changes there will mutate the original
        // object too.

        mutateVector(v);
        System.out.println("v = " + v);
        System.out.println("v2 = " + v2);
    }

    public static double mutatePrimitive(double i) {
        i = i + 100;
        return i;
    }

    public static void mutateVector(Vector2 vector2){
        vector2.x *= 2;
        vector2.y *= 2;
    }
}

