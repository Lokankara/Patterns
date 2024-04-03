package patterns.threads.executor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SinglePairThreadPool {
    protected static final CountDownLatch COL = new CountDownLatch(8);

    public void threadAndQueue()
            throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable taskSubmitter = Runner.getTaskSubmitter(COL);
        executorService.execute(taskSubmitter);

        TimeUnit.SECONDS.sleep(1);

        int workers = 6;
        ExecutorService workerExecutor = Executors.newFixedThreadPool(workers);
        CompletionService<Void> completionService =
                getVoidCompletionService(workerExecutor, workers);

        for (int i = 0; i < workers; i++) {
            completionService.take();
        }

        executorService.shutdown();
        workerExecutor.shutdown();

        executorService.awaitTermination(10, TimeUnit.SECONDS);
        workerExecutor.awaitTermination(10, TimeUnit.SECONDS);
    }

    private static CompletionService<Void> getVoidCompletionService(
            ExecutorService workerExecutor,
            int workers) {
        CompletionService<Void> completionService =
                new ExecutorCompletionService<>(workerExecutor);

        for (int i = 0; i < workers; i++) {
            completionService.submit(() -> {
                try {
                    Runner.start();
                } catch (InterruptedException e) {
                    Thread.currentThread()
                          .interrupt();
                } finally {
                    COL.countDown();
                }
                return null;
            });
        }
        return completionService;
    }
}
