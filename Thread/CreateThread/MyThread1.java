public class MyThread1 extends Thread {
    // 创建线程的第一种方式：
    // 1. 继承Thread类
    // 2. 重写run方法
    // 3. 调用start方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + "hello world");
        }
    }
}
