// 两个线程，交替打印1-100，一个打印奇数，一个打印偶数
public class ThreadAB {
    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    System.out.println("a锁");
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "Thread-A");

        Thread t2 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    System.out.println("b锁");
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "Thread-B");

        t1.start();
        t2.start();
    }
}
