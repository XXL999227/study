import java.util.PriorityQueue;

// https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
// topK问题，通常的解决思路是排序和堆
public class topK {
    public static void main(String[] args) {
        int[] nums = { 3, 2, 1, 5, 6, 4 };
        System.out.println(new topK().findKthLargest(nums, 2));
    }

    // 用java里的优先级队列初始化一个大小为k的小顶堆(默认就是从小到大的小顶堆)
    // 将数组前k个元素放入堆
    // 遍历后续元素，如果大于堆顶元素，则抛弃堆顶元素，当前元素入堆，如果小于，则不管
    public int findKthLargest(int[] nums, int k) {
        // 1、初始化一个堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        // 大顶堆，初始化时不带容量也可以
        // PriorityQueue<Integer> queue = new PriorityQueue<>(k, ((o1, o2) -> o2 - o1));
        // 2、将数组前k个元素放入堆
        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        // 3、遍历后续元素，如果大于堆顶元素，则抛弃堆顶元素，当前元素入堆，如果小于，则不管
        for (int j = k; j < nums.length; j++) {
            if (nums[j] > queue.peek()) {
                queue.poll();
                queue.add(nums[j]);
            }
        }
        return queue.peek();
    }
}
