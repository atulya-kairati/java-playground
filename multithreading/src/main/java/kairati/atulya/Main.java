package kairati.atulya;

import kairati.atulya.runnable.MyRunnable;
import kairati.atulya.thread.AnotherThread;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hi from main thread");


        Thread another = new AnotherThread();
        another.start(); //start can be only called once for an instance

        // anon class
        new Thread() {
            @Override
            public void run() {
                System.out.println("Hi from anon class thread");
            }
        }.start();

        // thread with runnable
        Thread myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();

        System.out.println("Bye from main thread");
    }
}
