package patterns.threads.executor;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.Executors.newScheduledThreadPool;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExecutorServiceManagerTest {

    @Test
    void singleThreadExecutorTest() {
        Executor executor = Executors.newSingleThreadExecutor();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        executor.execute(() -> System.out.println(Thread.currentThread().getName()));
        System.out.printf("Main %s%n", Thread.currentThread().getName());
        System.setOut(System.out);
        String actualOutput = outputStream.toString().trim();
        String expectedOutput = "Main " + Thread.currentThread().getName();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void fixedTheadPoolExecutorTest() {
        ThreadPoolExecutor service = (ThreadPoolExecutor) newFixedThreadPool(2);
        execute(service);
        execute(service);
        execute(service);
        assertEquals(2, service.getCorePoolSize());
        assertEquals(1, service.getQueue().size());
    }

    @Test
    void fixedTheadPoolExecutorGetResultTest()
            throws ExecutionException, InterruptedException {
        ExecutorService service = newFixedThreadPool(2);
        Future<String> future = service.submit(() -> "Executor");
        String result = future.get();
        assertEquals("Executor", result);
    }

    @Test
    void cachedTheadPoolExecutorGetResultTests()
            throws ExecutionException, InterruptedException {
        ThreadPoolExecutor service = (ThreadPoolExecutor) newCachedThreadPool();
        execute(service);
        execute(service);
        execute(service);
        assertEquals(3, service.getPoolSize());
        assertEquals(0, service.getQueue().size());
        Future<String> future = service.submit(() -> "Executor");
        String result = future.get();
        assertEquals("Executor", result);
    }

    @Test
    void scheduledTheadPoolExecutorGetResultTest()
            throws InterruptedException, ExecutionException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ScheduledExecutorService service = newScheduledThreadPool(2);
        ScheduledFuture<?> scheduledFuture =
                service.scheduleAtFixedRate(() -> countDownLatch.countDown(),
                                            2,
                                            1,
                                            TimeUnit.SECONDS);
        countDownLatch.await(2, TimeUnit.SECONDS);
        scheduledFuture.cancel(true);
        execute(service);
        execute(service);
        execute(service);
        service.schedule(() -> System.out.println("schedule"),
                         2,
                         TimeUnit.SECONDS);
        Future<String> future = service.submit(() -> "Executor");
        assertEquals("Executor", future.get());
    }

    @Test
    void cachedTheadPoolExecutorGetResultTest()
            throws ExecutionException, InterruptedException {
        ThreadPoolExecutor service =
                new ThreadPoolExecutor(0,
                                       5,
                                       60L,
                                       TimeUnit.SECONDS,
                                       new LinkedBlockingQueue<>());
        execute(service);
        execute(service);
        execute(service);
        assertEquals(1, service.getPoolSize());
        assertEquals(3, service.getQueue().size());
        Future<String> future = service.submit(() -> "Executor");
        String result = future.get();
        assertEquals("Executor", result);
    }

    @Test
    void atomicTheadPoolExecutorGetResultTest()
            throws ExecutionException, InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        ThreadPoolExecutor service = (ThreadPoolExecutor) newCachedThreadPool();
        Future<?> sleep = service.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            atomicInteger.set(3);

        });
        Future<?> submit = service.submit(() -> atomicInteger.set(3));
        Future<Boolean> submitted =
                service.submit(() -> atomicInteger.compareAndSet(1, 2));
        submit.get();
        submitted.get();
        sleep.get();
        assertEquals(3, atomicInteger.get());
    }

    private void execute(ExecutorService service) {
        service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            return null;
        });
    }
}
