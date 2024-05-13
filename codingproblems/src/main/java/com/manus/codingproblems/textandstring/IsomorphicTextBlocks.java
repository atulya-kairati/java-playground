package com.manus.codingproblems.textandstring;


/**
 * Write a program that checks if two text blocks are isomorphic.
 *
 * Text blocks are isomorphic if their strings are isomorphic
 *
 * Two strings are considered isomorphic if we can map every
 * character of the first string to every character of the second
 * string in a one-to-one fashion
 * (for instance, "xxyznnxiz" and "aavurraqu" are isomorphic).
 *
 * 1. essential white space characters should also be considered
 */
import java.util.*;

public class IsomorphicTextBlocks {

    static boolean isomorphicTextBlocks(String str1, String str2) {

        if (str1 == null || str2 == null) return false;
        if (str1.length() != str2.length()) return false;

        Map<Character, Character> matcher = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);

            if (matcher.containsKey(char1)) {
                if (matcher.get(char1) != char2) return false;
            }
            else {

                if (matcher.containsValue(char2)) return false;

                matcher.put(char1, char2);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String block1 = """
                hello
                 \s
                world \s
                """;

        String block2 = """
                ifttknssnbkdtgssn""";

        // here { ' '=s, r=d, d=g, e=f, w=b, h=i, \n=n, l=t, o=k}

        System.out.println(isomorphicTextBlocks(block1, block2));
    }
}