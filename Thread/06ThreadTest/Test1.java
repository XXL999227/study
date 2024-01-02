import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 一共有1000张电影票,可以在两个窗口领取,假设每次领取的时间为3000毫秒,
// 要求:请用多线程模拟卖票过程并打印剩余电影票的数量
public class Test1 {
    static class Window extends Thread {
        static int ticket = 0;

        static Lock lock = new ReentrantLock();

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    if (ticket == 1000) {
                        break;
                    } else {
                        Thread.sleep(3);
                        System.out.println(getName() + "卖出了第" + ++ticket + "张票");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();

        w1.setName("窗口1");
        w2.setName("窗口2");

        w1.start();
        w2.start();
    }
}
