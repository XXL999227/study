// 两个线程，交替打印1-100，一个打印奇数，一个打印偶数，
// 方法一：抢同一把锁，抢到的判断是否是自己的数，是则打印，否则释放锁
public class ThreadAB1 {
    private static int count = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    // System.out.println("a锁");
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "Thread-A");

        Thread t2 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    // System.out.println("b锁");
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
