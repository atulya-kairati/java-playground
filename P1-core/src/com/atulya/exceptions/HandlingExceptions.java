package com.atulya.exceptions;

public class HandlingExceptions {
    public static void main(String[] args) {

        // here we are not forced to handle but we can
        System.out.println(divide1(3, 4));

        try {
            System.out.println(divide2(3, 0));
        } catch (MyCheckedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("done");
        }
    }

    static double divide1(double a, double b) throws MyUncheckedException{

        if (b == 0){
            // since this is an unchecked exception
            // the compiler is not forcing us to handle it
            // **optionally** we can use throws to let the caller know
            // that a exception can be thrown
            throw new MyUncheckedException("Don't divide1 by zero");
        }

        return a / b;
    }
    static double divide2(double a, double b) throws MyCheckedException{
        // by using throws we have passed the responsibility of
        // handling the exception to the caller

        if (b == 0){
            // since this is a checked exception
            // the compiler is forcing us to handle it
            throw new MyCheckedException("Don't divide2 by zero");
        }

        return a / b;
    }


}
