import java.util.concurrent.locks.Lock;

class MyRunnable implements Runnable {
    final private int number;
    final private Lock lock;
    final private int[] totalSteps;

    public MyRunnable(int number, Lock lock, int[] totalSteps) {
        this.number = number;
        this.lock = lock;
        this.totalSteps = totalSteps;
    }

    @Override
    public void run() {
        int steps = CollatzCalculator.calculateCollatz(number);

        lock.lock();

        try {
            totalSteps[0] += steps;
        } finally {
            lock.unlock();
        }
    }
}
