public class MyThread extends Thread {
    // 重写构造方法

    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + "正在运行" + i);
        }
    }
}
