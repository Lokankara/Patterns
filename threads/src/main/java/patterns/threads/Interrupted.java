package patterns.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Interrupted {

    public static void main(String[] args)
            throws InterruptedException {
        CountDownLatch main = new CountDownLatch(1);
        CountDownLatch await = new CountDownLatch(1);

        Runnable runnable = () -> {
            log.info("Interrupted status {}",
                              Thread.currentThread().isInterrupted());
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    log.info(
                            "Worker {}", Thread.currentThread().getName());
                    await.countDown();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.info(
                            "InterruptedException, name: {}, status: {}",
                            Thread.currentThread().getName(),
                            Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    log.info(
                            "InterruptedException, name: {}, status: {}",
                            Thread.currentThread().getName(),
                            Thread.currentThread().isInterrupted());
                }
            }
            main.countDown();
        };
        Thread thread = new Thread(runnable);
        thread.start();
        log.info("MAIN {}", thread.getName());
        await.await();
        thread.interrupt();
        log.info("MAIN {}", thread.getName());
        main.await();
        log.info("App complete");
    }
}
