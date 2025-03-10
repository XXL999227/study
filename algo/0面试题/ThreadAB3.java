// 两个线程，交替打印1-100，一个打印奇数，一个打印偶数，
// 方法三，用object类中的wait和notify方法
// 一个线程打印后，让他休眠，同时唤醒另一个线程打印，他们交替执行
public class ThreadAB3 {
    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (count < 100) {
                // 这里的lock也可以用ThreadAB3.class，因为这个类是唯一的
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "偶数");

        Thread t2 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "奇数");

        t1.start();
        t2.start();
    }
}
