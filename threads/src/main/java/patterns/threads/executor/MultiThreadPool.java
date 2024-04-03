package patterns.threads.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MultiThreadPool {

    protected static final CountDownLatch COL = new CountDownLatch(8);

    public void threadAndQueue()
            throws Exception {
        ExecutorService workerExecutor;
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable taskSubmitter = Runner.getTaskSubmitter(COL);
        executorService.execute(taskSubmitter);

        TimeUnit.SECONDS.sleep(1);

        int workers = 6;
        workerExecutor = Executors.newFixedThreadPool(workers);

        List<Future<Void>> futures = new ArrayList<>();
        for (int i = 0; i < workers; i++) {
            futures.add(workerExecutor.submit(() -> {
                try {
                    Runner.start();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    COL.countDown();
                }
                return null;
            }));
        }

        for (Future<Void> future : futures) {
            future.get();
        }

        executorService.shutdown();
        workerExecutor.shutdown();

        executorService.awaitTermination(10, TimeUnit.SECONDS);
        workerExecutor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
