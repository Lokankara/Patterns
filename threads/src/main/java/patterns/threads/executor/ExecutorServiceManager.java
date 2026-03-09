package patterns.threads.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
public class ExecutorServiceManager {
    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> log.info(Thread.currentThread().getName()));
        log.info("Main {}n", Thread.currentThread().getName());
    }
}
