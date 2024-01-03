import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 在上一题基础上继续完成如下需求:
// 每次抽的过程中,不打印,抽完时一次性打印(随机)
// 在此次抽奖过程中,抽奖箱1总共产生了6个奖项。
// 分别为:10,20,100,500,2,300最高奖项为300元,总计额为932元
// 在此次抽奖过程中,抽奖箱2总共产生了6个奖项。
// 分别为:5,50,200,800,80,700最高奖项为800元,总计额为1835元
public class Test6 {
    static class MyThread extends Thread {
        List<Integer> list;
        List<Integer> list1 = new ArrayList<>();

        public MyThread(String name, List<Integer> list) {
            super(name);
            this.list = list;
        }

        @Override
        public void run() {
            while (true) {
                // 注意这里不能用this，因为this代表的是MyThread对象，而不是Test6对象
                // MyThread对象有两个，所以不能用this
                synchronized (Test6.class) {
                    if (list.size() == 0) {
                        System.out.println(getName() + "总共产生了" + list1.size() + "个奖项");
                        int max = list1.size() == 0 ? 0 : Collections.max(list1);
                        System.out.println("分别为:" + list1 +
                                "最高奖项为" + max + "元," +
                                "总计额为" + list1.stream().mapToInt(Integer::intValue).sum() + "元");
                        break;
                    } else {
                        Collections.shuffle(list);// 打乱集合中的元素
                        Integer prize = list.remove(list.size() - 1);// 移除集合中的最后一个元素,并返回该元素
                        list1.add(prize);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700);

        MyThread myThread1 = new MyThread("抽奖箱1", list);
        MyThread myThread2 = new MyThread("抽奖箱2", list);
        MyThread myThread3 = new MyThread("抽奖箱3", list);
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}
