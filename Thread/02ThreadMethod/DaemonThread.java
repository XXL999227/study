// 守护线程
public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        // 当其他非守护线程执行完毕后，守护线程陆陆续续也会执行完毕
        // 通俗的说，守护线程是为其他线程服务的，如果其他线程执行完毕，守护线程也就没有存在的必要了
        // 就像女神和备胎，女神不需要备胎的时候，备胎就没有存在的必要了
        // 就像垃圾回收器，就是一个守护线程，当其他线程执行完毕后，垃圾回收器也就没有存在的必要了
        // thread1.setName("女神");
        // thread2.setName("备胎");
        //
        // // 设置守护线程
        // thread2.setDaemon(true);
        //
        // thread1.start();
        // thread2.start();

        // // 线程礼让
        // Thread3 thread3 = new Thread3();
        // // 正常线程
        // Thread2 thread4 = new Thread2();
        // thread3.setName("线程3");
        // thread4.setName("线程4");
        // thread3.start();
        // thread4.start();

        // 线程插入
        Thread2 thread5 = new Thread2();
        thread5.setName("线程5");
        thread5.start();

        // 如果不加join，main线程和thread5线程是交替执行的
        // 加了join，main线程会等待thread5线程执行完毕后，再执行
        thread5.join();

        //
        for (int i = 0; i < 10; i++) {
            System.out.println("main线程" + i);
        }
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(getName() + "@" + i);
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(getName() + "#" + i);
            }
        }
    }

    static class Thread3 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(getName() + "$" + i);
                // 礼让线程，但是礼让不是完全礼让，还是有可能抢到执行权的
                Thread.yield();
            }
        }
    }
}
