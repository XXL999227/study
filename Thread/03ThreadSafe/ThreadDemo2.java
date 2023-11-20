
public class ThreadDemo2 {
    // 卖100张电影票，3个窗口，用同步方法实现
    static class MyRunnable implements Runnable {
        // 因为线程的创建方式不同，这里的多线程创建方式不是new Thread()，而是实现Runnable接口
        // 所以不需要将ticket声明为static
        int ticket = 0;

        @Override
        public void run() {
            while (true) {
                if (method()) break;
            }
        }

        private synchronized boolean method() {
            if (ticket == 100) {
                return true;
            } else {
                // 加个睡眠，避免线程跑太快
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket++;
                System.out.println(Thread.currentThread().getName() + "卖出了第" + ticket + "张票");
            }
            return false;
        }
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(myRunnable, "窗口1");
        Thread t2 = new Thread(myRunnable, "窗口2");
        Thread t3 = new Thread(myRunnable, "窗口3");

        t1.start();
        t2.start();
        t3.start();

        // StringBuilder是线程不安全的，但是快
        // StringBuffer是线程安全的，是因为它的方法都是加了synchronized
    }
}
