package ThreadSafe;

public class ThreadDemo {
    // 需求：卖100张电影票，3个窗口同时卖票

    static class MyThread extends Thread {
        // 将票数定义为static，保证每个线程都使用同一个票数
        static int ticket = 0;

        // 创建一个锁对象,一定要是同一个对象，所以定义为static
        static Object object = new Object();

        @Override
        public void run() {
            // 细节：synchronized要加在while循环里面，否则获取到锁的线程会一直买完所有票
            while (true) {
                // 这里也可以使用当前类的字节码对象，因为字节码对象是唯一的
                // synchronized (MyThread.class) {
                synchronized (object) {
                    if (ticket < 100) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        ticket++;
                        System.out.println(getName() + "正在卖第" + ticket + "张票");
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // 创建3个线程对象
        MyThread mt = new MyThread();
        MyThread mt2 = new MyThread();
        MyThread mt3 = new MyThread();

        // 起名字
        mt.setName("窗口1");
        mt2.setName("窗口2");
        mt3.setName("窗口3");

        // 启动线程
        mt.start();
        mt2.start();
        mt3.start();

        // 出现的问题：相同的票数出现了多次、出现了超出范围的票
        // 出现的原因：多个线程同时操作共享数据,线程睡眠时，其他线程也在操作共享数据
        // 出现的解决办法：同步代码块synchronized
    }
}
