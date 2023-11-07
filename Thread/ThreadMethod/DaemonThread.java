// 守护线程
public class DaemonThread {
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        // 当其他非守护线程执行完毕后，守护线程陆陆续续也会执行完毕
        // 通俗的说，守护线程是为其他线程服务的，如果其他线程执行完毕，守护线程也就没有存在的必要了
        // 就像女神和备胎，女神不需要备胎的时候，备胎就没有存在的必要了
        // 就像垃圾回收器，就是一个守护线程，当其他线程执行完毕后，垃圾回收器也就没有存在的必要了
        thread1.setName("女神");
        thread2.setName("备胎");

        // 设置守护线程
        thread2.setDaemon(true);

        thread1.start();
        thread2.start();
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
                System.err.println(getName() + "#" + i);
            }
        }
    }
}
