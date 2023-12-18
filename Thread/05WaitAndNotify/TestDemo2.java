import java.util.concurrent.ArrayBlockingQueue;

public class TestDemo2 {
    // 用阻塞队列实现生产者消费者模型
    private static class Cook extends Thread {
        private ArrayBlockingQueue<String> queue;

        /**
         * 构造方法，当cook初始化时，就需要传入一个队列，这样保证了cook和foodie使用的是同一个队列
         *
         * @param queue 队列
         */
        public Cook(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // 这里不加锁的原因是，队列本身就是线程安全的，put方法内部已经加锁了
                    queue.put("面条");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 这个打印语句会出现问题，因为语句放在了锁外面，所以会出现其他线程打印的情况？
                // 但是实际上我们的共享数据是没有问题的
                System.out.println("厨师做了一碗面条");
                // 这里的原因大概是：厨师做了一碗，然后打印，吃货消费后还没有来得及打印吃完了，
                // cpu资源又被厨师拿到，然后厨师又做了一碗，然后打印，就出现了两次或者多次打印的情况
            }
        }
    }

    private static class Foodie extends Thread {
        private ArrayBlockingQueue<String> queue;

        /**
         * 构造方法
         *
         * @param queue 队列
         */
        public Foodie(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // 这里不加锁的原因是，队列本身就是线程安全的，take方法内部已经加锁了
                    String food = queue.take();
                    System.out.println("吃货吃了一碗" + food);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        Cook cook = new Cook(queue);
        Foodie foodie = new Foodie(queue);

        cook.setName("厨师");
        foodie.setName("吃货");

        cook.start();
        foodie.start();
        Thread.State state = cook.getState();
    }
}
