// 两个线程，交替打印1-100，一个打印奇数，一个打印偶数，
// 方法二，维护一个flag，一个线程打印完后，将flag置为false，另一个线程打印完后，将flag置为true
public class ThreadAB2 {
    private static boolean flag = true;
    private static int count = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (count < 100) {
                if (flag) {
                    System.out.println(count);
                    count++;
                }
            }
        }, "线程A");

        Thread t2 = new Thread(() -> {
            while (count < 100) {
                if (!flag) {
                    System.out.println(count);
                    count++;
                }
            }
        }, "线程B");

        t1.start();
        t2.start();
    }
}
