import java.util.*;

// 有一个抽奖池,该抽奖池中存放了奖励的金额,该抽奖池中的奖项为
// {10,5,20,50,100,200,500,800,2,80,300,700};
// 创建两个抽奖箱(线程)设置线程名称分别为“抽奖箱1”,“抽奖箱2”
// 随机从抽奖池中获取奖项元素并打印在控制台上,格式如下:
// 每次抽出一个奖项就打印一个(随机)
// 抽奖箱1又产生了一个10元大奖
// 抽奖箱1又产生了一个100元大奖
// 抽奖箱1又产生了一个200元大奖
// 抽奖箱1又产生了一个800元大奖
// 抽奖箱2又产生了一个700元大奖
public class Test5 {
    static class Mythread extends Thread {
        private static List<Integer> list = new ArrayList<>(Arrays.asList(10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700));

        public Mythread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (true) {
                synchronized (Test5.class) {
                    if (list.size() == 0) {
                        System.out.println("over");
                        break;
                    } else {
                        Collections.shuffle(list);// 打乱集合中的元素
                        Integer prize = list.remove(list.size() - 1);// 移除集合中的最后一个元素,并返回该元素
                        System.out.println(getName() + "又产生了一个" + prize + "元大奖");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Mythread mythread1 = new Mythread("抽奖箱1");
        Mythread mythread2 = new Mythread("抽奖箱2");
        mythread1.start();
        mythread2.start();
    }
}
