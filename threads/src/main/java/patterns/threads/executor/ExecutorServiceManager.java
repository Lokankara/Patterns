package patterns.threads.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorServiceManager {
    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(()-> System.out.println(Thread.currentThread().getName()));
        System.out.printf("Main %s%n", Thread.currentThread().getName());
    }
}
