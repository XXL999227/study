import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

// 抢红包也用到了多线程。
// 假设:100块,分成了3个包,现在有5个人去抢。
// 其中,红包是共享数据。
// 5个人是5条线程。
// 打印结果如下:
// XXX抢到了XXX元
// XXX抢到了XXX元
// XXX抢到了XXX元
// XXX没抢到
// XXX没抢到
public class Test4 {
    static class Mythread extends Thread {
        private static BigDecimal money = new BigDecimal("100.00");
        private static int count = 3;

        public Mythread(String name) {
            super(name);
        }
        @Override
        public void run() {
            synchronized (Test4.class) {
                if (count == 0) {
                    System.out.println(getName() + "没抢到红包");
                } else if (count == 1) {
                    System.out.println(getName() + "抢到了" + money);
                    count--;
                } else {
                    SecureRandom sc;// 安全随机数
                    try {
                        sc = SecureRandom.getInstance("SHA1PRNG");// 获取强安全随机数对象
                    } catch (NoSuchAlgorithmException e) {
                        sc= new SecureRandom();// 弱安全随机数对象
                    }
                    // 红包最小值为0.01，所以第一个红包最大为99.98，这样才能保证剩下的红包还能分成两个
                    // 100-（3-1）*0.01=99.98
                    BigDecimal price = new BigDecimal(String.format("%.2f", sc.nextDouble() * (money.doubleValue() - (count - 1) * 0.01)));
                    if (price.compareTo(new BigDecimal("0.01")) < 0) {// 如果抽到的金额小于0.01，则设置为0.01
                        price = new BigDecimal("0.01");
                    }
                    money = money.subtract(price);
                    count--;
                    System.out.println(getName() + "抢到了" + price);
                }
            }
        }
    }

    public static void main(String[] args) {
        Mythread mythread1 = new Mythread("张三");
        Mythread mythread2 = new Mythread("李四");
        Mythread mythread3 = new Mythread("王五");
        Mythread mythread4 = new Mythread("赵六");
        Mythread mythread5 = new Mythread("田七");
        mythread1.start();
        mythread2.start();
        mythread3.start();
        mythread4.start();
        mythread5.start();
    }
}
