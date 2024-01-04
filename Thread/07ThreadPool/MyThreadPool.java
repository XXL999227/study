import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 自定义线程池
public class MyThreadPool {
    /*
     ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
        (核心线程数量,最大线程数量,空闲线程最大存活时间,单位,任务队列,创建线程工厂,任务的拒绝策略);

        参数一:核心线程数量              不能小于0
        参数二:最大线程数                   不能小于0，最大线程数必须大于等于核心线程数
        参数三:空闲线程最大存活时间             不能小于0
        参数四:时间单位                    TimeUnit
        参数五:任务队列                    不能为null
        参数六:创建线程工厂                  不能为null
        参数七:任务的拒绝策略                 不能为null
    */

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,
                10,// 最大线程数,这里最多可以创建10-5=5个临时线程
                60,// 空闲线程最大存活时间
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),// 任务队列,使用有界队列,最大任务数为10
                Executors.defaultThreadFactory(),// 使用默认的线程工厂，其实源码中也是new Thread()
                new ThreadPoolExecutor.AbortPolicy()// 拒绝策略是静态内部类，四种拒绝策略
        );

        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running.");
            }
        });
    }
}
