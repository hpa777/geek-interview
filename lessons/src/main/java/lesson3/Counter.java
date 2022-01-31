package lesson3;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private long counter = 0L;

    private final ReentrantLock locker;

    public Counter(ReentrantLock locker) {
        this.locker = locker;
    }

    public void increase() {
        locker.lock();
        counter++;
        locker.unlock();
    }

    public long getCounter() {
        return counter;
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Counter counter = new Counter(lock);
        for (int i = 0; i < 200; i++) {
            new Thread(()-> {
                for (int j = 0; j < 1000; j++) {
                    counter.increase();
                }
            }).start();
        }
        Thread.sleep(3000);
        System.out.println("Counter:" + counter.getCounter());
    }
}
