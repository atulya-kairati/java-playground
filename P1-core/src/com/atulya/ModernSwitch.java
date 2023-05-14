package com.atulya;

enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSADAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

public class ModernSwitch {
    public static void main(String[] args) {

        Day day = Day.SATURDAY;

        // data types that can be used with switch
        // char, byte, short, int, Character, Byte, Short, Integer, String, or an enum
        // all primitives and their wrappers except floating points
        // enums and strings

        // old switch
        switch (day) {

            case MONDAY:
            case WEDNESDAY:
            case FRIDAY:
                System.out.println("Work from home! :)");
                break;

            case TUESDAY:
            case THURSADAY:
                System.out.println("Go to office :(");
                break;

            case SATURDAY:
            case SUNDAY:
                System.out.println("Keep sleeping :p");
                break;

            default:
                System.out.println("What day is that?");
        }

        // new switch

        String result = switch (day) {
            case MONDAY, WEDNESDAY, FRIDAY -> "Work from home! :)";
            case TUESDAY, THURSADAY -> "Go to office :(";
            case SATURDAY, SUNDAY -> "Keep sleeping :p";
            default -> "What day is that?";
        };

        System.out.println("result = " + result);
    }
}
