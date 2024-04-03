package patterns.threads.executor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SinglePairThreadPoolTest {

    @Test
    void testMultiThreadPool()
            throws Exception {
        SinglePairThreadPool threadPool = new SinglePairThreadPool();
        threadPool.threadAndQueue();
        assertEquals(1, (SinglePairThreadPool.COL.getCount()));
    }

    @Test
    void testSingleThreadPool()
            throws Exception {
        MultiThreadPool threadPool = new MultiThreadPool();
        threadPool.threadAndQueue();
        assertEquals(1, MultiThreadPool.COL.getCount());
    }
}
