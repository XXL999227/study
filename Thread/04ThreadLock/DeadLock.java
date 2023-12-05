// 死锁
public class DeadLock {
    static class MyThread extends Thread {
        static Object lockA = new Object();
        static Object lockB = new Object();

        @Override
        public void run() {
            // 这段代码会发生死锁，因为线程A和线程B都在等待对方释放锁
            while (true) {
                if ("线程A".equals(Thread.currentThread().getName())){
                    synchronized (lockA) {
                        System.out.println("线程A拿到了锁A，准备获取锁B");
                        synchronized (lockB) {
                            System.out.println("线程A获取到锁A和锁B");
                        }
                    }
                } else if ("线程B".equals(getName())) {
                    if ("线程B".equals(Thread.currentThread().getName())){
                        synchronized (lockB) {
                            System.out.println("线程B拿到了锁B，准备获取锁A");
                            synchronized (lockA) {
                                System.out.println("线程B获取到锁A和锁B");
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread threadA = new MyThread();
        Thread threadB = new MyThread();

        threadA.setName("线程A");
        threadB.setName("线程B");

        threadA.start();
        threadB.start();
    }
}
