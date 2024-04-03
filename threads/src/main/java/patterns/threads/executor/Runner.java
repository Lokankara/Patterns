package patterns.threads.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Runner {
    public static final int QUEUE_CAPACITY = 10;
    public static final BlockingQueue<Pair<String, String>> TASK_QUEUE =
            new LinkedBlockingQueue<>(QUEUE_CAPACITY);

    public static void start()
            throws InterruptedException {
        while (true) {
            Pair<String, String> task = TASK_QUEUE.poll(1, TimeUnit.SECONDS);

            if (task == null) {
                break;
            }

            System.out.printf("Worker: %s %s %s%n",
                              Thread.currentThread().getName(),
                              task.key(),
                              task.value());
            TimeUnit.SECONDS.sleep(4);
        }
    }

    public static Runnable getTaskSubmitter(CountDownLatch COL) {
        return () -> {
            try {
                System.out.printf("%s Started task submit%n",
                                  Thread.currentThread().getName());
                for (int i = 0; i < 10; i++) {
                    String proxy = "Proxy " + i;
                    String scenario = "Scenario " + i;
                    TASK_QUEUE.put(new Pair<>(proxy, scenario));
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                COL.countDown();
            }
        };
    }
}
