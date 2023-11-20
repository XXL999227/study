public class MyThread2 implements Runnable {
    // 创建线程的第二种方式：
    // 1. 实现Runnable接口
    // 2. 重写run方法
    // 3. 创建Thread对象
    // 4. 调用start方法
    @Override
    public void run() {
        // 获取当前线程对象
        Thread t = Thread.currentThread();
        for (int i = 0; i < 100; i++) {
            System.out.println(t.getName() + "hello world");
        }
    }
}
