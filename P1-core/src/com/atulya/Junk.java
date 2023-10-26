package com.atulya;

import java.util.Arrays;

class FunctionalMain {
    public static void main(String... args) {
        String num1 = "234332";
        String num2 = "243433";

        int width = num1.length() + num2.length();
        int[] row = new int[width];

        for (int i = 0; i < num2.length(); i++) {
            int d2 = num2.charAt(i) - 48;

            int carry = 0;
            int ptr = num2.length() - i - 1;

            for (int j = num1.length() - 1; j >= 0; j--) {
                int d1 = num1.charAt(j) - 48;
                int mul = row[ptr] + d1 * d2 + carry;
                System.out.println(row[ptr] + " + " + d1 + " * " + d2 + " + " + carry + " = " + mul);
                row[ptr] = mul % 10;
                carry = mul / 10;
                ptr++;
            }

            while (carry != 0) {
                row[ptr] += carry;
                carry = row[ptr] / 10;
                row[ptr] %= 10;
                ptr++;
            }

            System.out.println(Arrays.toString(row));

        }

        StringBuilder sb = new StringBuilder();

        for (int n : row) sb.append(n);

        sb = sb.reverse();

        while (sb.charAt(0) == '0') sb.deleteCharAt(0);

        System.out.println(sb.toString());
    }
}