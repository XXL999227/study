/**
 * 厨师类
 *
 * @author xxl
 * @since 2023/12/12
 */
public class Cook extends Thread{
    @Override
    public void run() {
        /*
         * 1、循环
         * 2、同步代码块
         * 3、判断共享数据是否到了末尾（到了末尾）
         * 4、判断共享数据没有到末尾（核心代码逻辑）
         */

        while (true) {// 1、循环
            synchronized (Desk.lock) {// 2、同步代码块
                // 3、判断数据是否到末尾
                if (Desk.foodCount == 0){
                    break;
                }
                // 判断桌子上是否有食物
                if (Desk.foodFlag == 1) {
                    // 如果有,就等待
                    try {
                        Desk.lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    // 如果没有食物，就制作食物
                    System.out.println("厨师做了一碗面条");
                    // 修改桌子上食物状态
                    Desk.foodFlag = 1;
                    // 叫醒等待的消费者开吃
                    Desk.lock.notifyAll();
                }
            }
        }
    }
}
