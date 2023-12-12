/**
 * 吃货类
 *
 * @author xxl
 * @since 2023/12/12
 */
public class Foodie extends Thread {
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
                if (Desk.foodFlag == 0) {
                    // 如果没有,就等待
                    try {
                        Desk.lock.wait();// 当前线程和锁进行绑定
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    // 如果有食物，就开吃
                    System.out.println("吃货吃了一碗，还能再吃"+ --Desk.foodCount + "碗");
                    // 修改桌子上食物状态为没有食物了
                    Desk.foodFlag = 0;
                    // 叫醒服务端去做饭
                    Desk.lock.notifyAll();// 唤醒和这把锁绑定的所有线程，这里不可能唤醒操作系统所有等待的线程
                }
            }
        }
    }
}
