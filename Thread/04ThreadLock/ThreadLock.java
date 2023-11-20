import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLock {

    static class MyThread extends Thread {

        static int ticket = 0;

        // 因为是通过new Threa()的方式创建的线程，所以这里要设置为静态的
        // 否则就会有三把锁
        static Lock lock = new ReentrantLock();

        @Override
        public void run() {
            while (true) {
                // 同步代码块的方式
                // synchronized (ReentrantLock.class) {

                lock.lock();
                try {
                    if (ticket == 100) {
                        break;
                    } else {
                        Thread.sleep(100);
                        ticket++;
                        System.out.println(Thread.currentThread().getName() + "在卖第" + ticket + "张票");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
                // }
            }
        }
    }

    public static void main(String[] args) {
        // 用jdk1.5的Lock实现
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
