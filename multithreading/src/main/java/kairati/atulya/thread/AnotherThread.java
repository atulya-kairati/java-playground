package kairati.atulya.thread;

public class AnotherThread extends Thread{
    @Override
    public void run() {
        System.out.println("Hi from another thread!");
    }
}
