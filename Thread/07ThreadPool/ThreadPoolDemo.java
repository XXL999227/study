import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running.");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        // 创建一个无线程上限的线程池，（最大线程数为Integer.MAX_VALUE）
        ExecutorService pool = Executors.newCachedThreadPool();
        // Executors类中还有很多创建线程池的方法，可以根据需要选择

        pool.submit(new MyTask());
        Thread.sleep(1000);
        pool.submit(new MyTask());
        Thread.sleep(1000);
        pool.submit(new MyTask());
        Thread.sleep(1000);
        pool.submit(new MyTask());
        Thread.sleep(1000);
        pool.submit(new MyTask());

        // 关闭线程池,开发中一般不会关闭线程池
        // pool.shutdown();
        System.out.println("=====================================");
        // 创建一个固定大小的线程池
        ExecutorService pool2 = Executors.newFixedThreadPool(3);
        pool2.submit(new MyTask());
        pool2.submit(new MyTask());
        pool2.submit(new MyTask());
        pool2.submit(new MyTask());
        pool2.submit(new MyTask());
    }
}
