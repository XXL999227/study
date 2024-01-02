// 有100份礼品,两人同时发送,当剩下的礼品小于10份的时候则不再送出。
// 利用多线程模拟该过程并将线程的名字和礼物的剩余数量打印出来.
public class Test2 {
    static class Gift implements Runnable {
        static int gift = 100;

        @Override
        public void run() {
            while (true) {
                synchronized (Test2.class) {
                    if (gift == 10) {
                        break;
                    } else {
                        System.out.println(Thread.currentThread().getName() + "发送了第" + (101 - gift) + "份礼物，剩余" + --gift + "份");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Gift gift = new Gift();
        Thread thread1 = new Thread(gift);
        Thread thread2 = new Thread(gift);

        thread1.setName("同学1");
        thread2.setName("同学2");

        thread1.start();
        thread2.start();
    }
}
