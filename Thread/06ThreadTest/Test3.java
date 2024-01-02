// 同时开启两个线程,共同获取1-100之间的所有数字。
// 要求:将输出所有的奇数。
public class Test3 {
    static class MyThread extends Thread {
        static int count = 1;

        @Override
        public void run() {
            while (true) {
                synchronized (Test3.class) {
                    if (count == 100) {
                        break;
                    } else if (count % 2 == 1) {
                        System.out.println(getName() + "输出数字:" + count++);
                    } else {
                        count++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        myThread1.start();
        myThread2.start();
    }
}
