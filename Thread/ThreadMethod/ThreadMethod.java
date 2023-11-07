// 线程的常用方法
public class ThreadMethod {
    public static void main(String[] args) throws InterruptedException {
        // MyThread mt = new MyThread();
        // mt.start();

        // 获取线程名称
        // System.out.println("获取线程名称" + mt.getName());

        // 设置线程名称, 也可以在构造方法中设置,如果不设置,默认为Thread-数字，数字从0开始
        // mt.setName("线程1");
        // System.out.println("设置线程名称为" + mt.getName());

        // 获取当前线程对象，哪条线程执行到这个方法，就返回哪条线程的对象
        // 当jvm启动，会自动启动多条线程，main方法的线程对象就是主线程对象，他的作用是调用main方法，执行里面的代码
        // 在以前，我们的代码都是运行在main线程中
        // System.out.println("获取当前线程对象" + Thread.currentThread().getName());

        // 线程休眠，让线程进入休眠状态，进入阻塞状态，让出cpu的执行权，让其他线程执行，时间到了，线程自动醒来
        // System.out.println("线程休眠");
        // Thread.sleep(5000);
        // System.out.println("线程休眠结束");

        // 线程优先级,min = 1, max = 10, 默认为5,main线程默认为5
        MyThread myThread1 = new MyThread("线程1");
        MyThread myThread2 = new MyThread("线程222");
        // System.out.println("线程1的优先级" + myThread1.getPriority());

        // 设置线程优先级,优先级高的线程获取cpu执行权的概率高，但是不代表优先级高的线程一定会先执行完
        myThread1.setPriority(1);
        myThread2.setPriority(10);
        myThread1.start();
        myThread2.start();
    }
}
