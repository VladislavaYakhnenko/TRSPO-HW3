import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        int N = 1000;
        int numThreads = 4;
        int[] totalSteps = new int[]{0};

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        Lock lock = new ReentrantLock();

        for (int i = 1; i <= N; i++) {
            executorService.submit(new MyRunnable(i, lock, totalSteps));
        }

        executorService.shutdown();

        try {
            long timeoutMillis = 60000;
            boolean terminated = executorService.awaitTermination(timeoutMillis, TimeUnit.MILLISECONDS);

            if (!terminated) {
                System.out.println("Час очікування вичерпано. Деякі потоки можуть ще працювати.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double averageSteps = (double) totalSteps[0] / N;
        System.out.println("Середня кількість кроків: " + averageSteps);
    }
}