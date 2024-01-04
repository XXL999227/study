import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
在上一题基础上继续完成如下需求:
在此次抽奖过程中,抽奖箱1总共产生了6个奖项,分别为:10,20,100,500,2,300
最高奖项为300元,总计额为932元
在此次抽奖过程中,抽奖箱2总共产生了6个奖项,分别为:5,50,200,800,80,700
最高奖项为800元,总计额为1835元
在此次抽奖过程中,抽奖箱2中产生了最大奖项,该奖项金额为800元，
此处为难点，怎么拿到两个线程中的最大值？（实现callable接口，能拿到线程的返回值）
以上打印效果只是数据模拟,实际代码运行的效果会有差异
*/
public class Test7 {
    static class MyCallable implements Callable<Integer> {
        List<Integer> list;

        public MyCallable(List<Integer> list) {
            this.list = list;
        }

        @Override
        public Integer call() throws Exception {
            ArrayList<Integer> list1 = new ArrayList<>();
            int max;
            while (true) {
                // 注意这里不能用this，因为this代表的是MyThread对象，而不是Test6对象
                // MyThread对象有两个，所以不能用this
                synchronized (Test7.class) {
                    if (list.size() == 0) {
                        System.out.println(Thread.currentThread().getName() + "总共产生了" + list1.size() + "个奖项");
                        max = list1.size() == 0 ? 0 : Collections.max(list1);
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
            return max;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700);

        // 创建多线程要运行的参数对象
        MyCallable myCallable = new MyCallable(list);

        // 创建多线程运行的管理者对象
        FutureTask<Integer> futureTask1 = new FutureTask<>(myCallable);
        FutureTask<Integer> futureTask2 = new FutureTask<>(myCallable);

        // 创建多线程对象
        Thread thread1 = new Thread(futureTask1, "抽奖箱1");
        Thread thread2 = new Thread(futureTask2, "抽奖箱2");
        thread1.start();
        thread2.start();

        int max = futureTask1.get() > futureTask2.get() ? futureTask1.get() : futureTask2.get();
        System.out.println("在此次抽奖过程中," + (futureTask1.get() > futureTask2.get() ? "抽奖箱1" : "抽奖箱2")
                + "中产生了最大奖项,该奖项金额为" + max + "元");
    }
}
