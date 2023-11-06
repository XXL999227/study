import java.util.concurrent.Callable;

public class MyThread3 implements Callable<Integer> {
    // 创建线程的第三种方式：
    // 1. 实现Callable接口
    // 2. 重写call方法(有返回值)
    // 3. 创建FutureTask对象
    // 4. 创建Thread对象
    // 5. 调用start方法
    // 6. 通过FutureTask对象获取返回值
    @Override
    public Integer call() throws Exception {
        // 累加1-100的和
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }

        return sum;
    }
}
