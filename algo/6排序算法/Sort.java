import java.util.Arrays;

// 可以参考leetcode 912题排序数组
//https://leetcode.cn/problems/sort-an-array/submissions/
public class Sort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};

        System.out.println(Arrays.toString(arr));
    }

    // 选择排序(从右边未排序的数组选择最小的元素放到左边已排序的末尾)
    public int[] selectionSort(int[] nums) {
        // 定义一个sortedIndex，
        // 索引 < sortedIndex 的元素都是已排序的
        // 索引 >= sortedIndex 的元素都是未排序的
        int sortedIndex = 0;
        while (sortedIndex < nums.length) {
            // 找到索引 >= sortedIndex 这个范围内的最小值
            int minIndex = sortedIndex;
            for (int i = sortedIndex; i < nums.length; i++) {
                if (nums[i] < nums[minIndex]) {
                    minIndex = i;
                }
            }
            // 将最小值和sortedIndex对应的值交换
            int tmp = nums[sortedIndex];
            nums[sortedIndex] = nums[minIndex];
            nums[minIndex] = tmp;
            // sortedIndex加1
            sortedIndex++;
        }
        return nums;
    }

    // 选择排序第一次优化，让选择排序获得稳定性
    public int[] selectionSort2(int[] nums) {
        // 定义一个sortedIndex
        // 索引 < sortedIndex 的元素都是已排序的
        // 索引 >= sortedIndex 的元素都是未排序的
        int sortedIndex = 0;
        while (sortedIndex < nums.length) {
            // 找到索引 >= sortedIndex 这个范围内的最小值
            int minIndex = sortedIndex;
            for (int i = sortedIndex; i < nums.length; i++) {
                if (nums[i] < nums[minIndex]) {
                    minIndex = i;
                }
            }
            // 将最小值和sortedIndex对应的值交换
            // int tmp = nums[sortedIndex];
            // nums[sortedIndex] = nums[minIndex];
            // nums[minIndex] = tmp;

            // 不能直接交换，会破坏稳定性，做一下数据搬迁即可
            int minVal = nums[minIndex];
            for (int i = minIndex; i > sortedIndex; i--) {
                nums[i] = nums[i - 1];
            }
            nums[sortedIndex] = minVal;
            // sortedIndex加1
            sortedIndex++;
        }
        return nums;
    }

    // 冒泡
    public int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }

    // 冒泡，优化一下，提前终止判断
    public int[] bubbleSort2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = true;
                }
            }
            // 如果一次都没交换过，说明数组本来就有序，可以提前终止
            if (!flag) {
                return nums;
            }
        }
        return nums;
    }

    // 冒泡排序的另一种写法,用选择排序的思路优化
    public int[] bubbleSort3(int[] nums) {
        int sortIndex = 0;
        while (sortIndex < nums.length) {
            for (int i = nums.length - 1; i > sortIndex; i--) {
                if (nums[i] < nums[i - 1]) {
                    int tmp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = tmp;
                }
            }
            sortIndex++;
        }
        return nums;
    }

    // 对选择排序进一步优化，向左侧有序数组中插入元素
    // 这个算法有另一个名字，叫做插入排序
    public int[] insertionSort(int[] nums) {
        // 维护[0, sortedIndex)是有序数组
        int sortedIndex = 0;
        // 像打牌一样，将抽到的牌插入到已经排序好的牌中
        while (sortedIndex < nums.length) {
            // 从后往前遍历，将nums[sortedIndex]放到它应该排序的位置
            for (int i = sortedIndex; i > 0; i--) {
                if (nums[i] < nums[i - 1]) {
                    // 交换
                    int temp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = temp;
                } else {
                    // 由于[0, sortedIndex)是有序数组，所以如果
                    // nums[sortedIndex]已经不比它前一个数小，就可以跳出本次循环了
                    break;
                }
            }
            sortedIndex++;
        }
        return nums;
    }

    // 快速排序，关键点在哨兵划分成子数组，递归实现
    public int[] quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        // 如果子数组长度小于等于1，就不用排序了
        if (left >= right) {
            return;
        }
        // 哨兵划分成两个子数组，返回哨兵的索引
        int pivotIndex = partition(nums, left, right);
        // 递归排序左子数组
        quickSort(nums, left, pivotIndex - 1);
        // 递归排序右子数组
        quickSort(nums, pivotIndex + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= nums[left]) { // j从右往左找到第一个小于哨兵的数
                j--;
            }
            while (i < j && nums[i] <= nums[left]) { // i从左往右找到第一个大于哨兵的数
                i++;
            }
            swap(nums, i, j);// 交换这两个数
        }
        swap(nums, left, i);// 此时i等于j，交换基准数和i
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
