/**
 * 桌子，用于控制程序的执行
 *
 * @author xxl
 * @since 2023/12/12
 */
public class Desk {
    /**
     * 是否有食物标志 0：没有 1：有
     */
    public static int foodFlag = 0;

    /**
     * 程序执行一次一个人的最大吃食物数量
     */
    public static int foodCount = 10;

    /**
     * 锁
     */
    public static Object lock = new Object();
}
