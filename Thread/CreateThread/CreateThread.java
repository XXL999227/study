import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CreateThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // System.out.println("方法一：继承Thread类，重写run方法，调用start方法");
        // // 创建线程对象
        // MyThread1 t1 = new MyThread1();
        // MyThread1 t2 = new MyThread1();
        // t1.setName("线程1");
        // t2.setName("线程2");
        // // 启动线程
        // t1.start();
        // t2.start();
        // // 主线程
        // for (int i = 0; i < 100; i++) {
        //     System.out.println("main" + i);
        // }

        // System.out.println("方法二：实现Runnable接口，重写run方法，创建Thread对象，调用start方法");
        // // 创建线程对象
        // // 表示线程要执行的任务
        // MyThread2 mt1 = new MyThread2();
        //
        // // 创建Thread对象
        // Thread t3 = new Thread(mt1);
        // Thread t4 = new Thread(mt1);
        // // t3.setName("线程3");
        // // t4.setName("线程4");
        // // 启动线程
        // t3.start();
        // t4.start();

        System.out.println("方法三：实现Callable接口，重写call方法，创建FutureTask对象，创建Thread对象，调用start方法，通过FutureTask对象获取返回值");
        // 创建线程对象
        // 表示线程要执行的任务
        MyThread3 mt3 = new MyThread3();
        // 创建FutureTask对象,用来管理多线程运行结果
        FutureTask<Integer> task = new FutureTask<>(mt3);
        // 创建Thread对象
        Thread t5 = new Thread(task);
        t5.start();
        // 通过FutureTask对象获取返回值
        Integer result = task.get();
        System.out.println(result);
    }
}