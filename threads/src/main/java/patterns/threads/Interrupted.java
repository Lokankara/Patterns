package patterns.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Interrupted {

    public static void main(String[] args)
            throws InterruptedException {
        CountDownLatch main = new CountDownLatch(1);
        CountDownLatch await = new CountDownLatch(1);

        Runnable runnable = () -> {
            System.out.printf("Interrupted status %s%n",
                              Thread.currentThread().isInterrupted());
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.printf(
                            "Worker %s%n", Thread.currentThread().getName());
                    await.countDown();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.printf(
                            "InterruptedException, name: %s, status: %s%n",
                            Thread.currentThread().getName(),
                            Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.printf(
                            "InterruptedException, name: %s, status: %s%n",
                            Thread.currentThread().getName(),
                            Thread.currentThread().isInterrupted());
                }
            }
            main.countDown();
        };
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.printf("MAIN %s%n", thread.getName());
        await.await();
        thread.interrupt();
        System.out.printf("MAIN %s%n", thread.getName());
        main.await();
        System.out.println("App complete");
    }
}
