import java.util.*;

//水壶问题 https://leetcode.cn/problems/water-and-jug-problem/description/
// 有两个水壶，容量分别为 x 和 y 升。水的供应是无限的。确定是否有可能使用这两个壶准确得到 target 升。
//
// 你可以：
//
// 装满任意一个水壶
// 清空任意一个水壶
// 将水从一个水壶倒入另一个水壶，直到接水壶已满，或倒水壶已空。
public class Water {
    // bfs
    public boolean canMeasureWater(int x, int y, int target) {
        // 用一个长度为2的list保存 X、Y 此时的水量
        Queue<List<Integer>> queue = new LinkedList<>();
        // 将出现过的组合加入到hashSet中，后续用于终止出现过的组合
        Set<List<Integer>> set = new HashSet<>();
        // 初始情况，此时，x，y水量为0，压入队尾
        queue.offer(Arrays.asList(0, 0));
        while (!queue.isEmpty()) {
            // 取出队列头元素
            List<Integer> temp = queue.poll();
            // temp.get(0)是x当前水量，temp.get(1)是y当前水量
            Integer curX = temp.get(0);
            Integer curY = temp.get(1);
            if (curX == target || curY == target || (curX + curY) == target) {
                // 如果x当前水量或者y当前水量，或者他们的合等于target，说明找到了
                return true;
            }

            //1、给x加满水
            List<Integer> condition1 = Arrays.asList(x, curY);
            if (!set.contains(condition1)) {// 当前组合没出现过才添加进去
                set.add(condition1);
                queue.offer(condition1);
            }
            //2、给y加满水
            List<Integer> condition2 = Arrays.asList(curX, y);
            if (!set.contains(condition2)) {
                set.add(condition2);
                queue.offer(condition2);
            }
            //3、x倒空
            List<Integer> condition3 = Arrays.asList(0, curY);
            if (!set.contains(condition3)) {
                set.add(condition3);
                queue.offer(condition3);
            }
            //4、y倒空
            List<Integer> condition4 = Arrays.asList(curX, 0);
            if (!set.contains(condition4)) {
                set.add(condition4);
                queue.offer(condition4);
            }
            //5、x倒入y，直到x倒空或者y满
            List<Integer> condition5 = (curX + curY >= y) ?
                    Arrays.asList(curX + curY - y, y) :
                    Arrays.asList(0, curX + curY);
            if (!set.contains(condition5)) {
                set.add(condition5);
                queue.offer(condition5);
            }
            //6、y倒入x，直到y倒空或者x满
            List<Integer> condition6 = (curX + curY >= x) ?
                    Arrays.asList(x, curX + curY - x) :
                    Arrays.asList(curX + curY, 0);
            if (!set.contains(condition6)) {
                set.add(condition6);
                queue.offer(condition6);
            }
        }
        return false;
    }

    // dfs,将队列改为栈，注释复制上面的代码
    public boolean canMeasureWater2(int x, int y, int target) {
        // 用一个长度为2的list保存 X、Y 此时的水量
        Stack<List<Integer>> stack = new Stack<>();
        // 将出现过的组合加入到hashSet中，后续用于终止出现过的组合
        Set<List<Integer>> set = new HashSet<>();
        // 初始情况，此时，x，y水量为0，压入栈
        stack.push(Arrays.asList(0, 0));
        while (!stack.isEmpty()) {
            // 取出栈顶元素
            List<Integer> temp = stack.pop();
            // temp.get(0)是x当前水量，temp.get(1)是y当前水量
            Integer curX = temp.get(0);
            Integer curY = temp.get(1);
            if (curX == target || curY == target || (curX + curY) == target) {
                // 如果x当前水量或者y当前水量，或者他们的合等于target，说明找到了
                return true;
            }

            //1、给x加满水
            List<Integer> condition1 = Arrays.asList(x, curY);
            if (!set.contains(condition1)) {// 当前组合没出现过才添加进去
                set.add(condition1);
                stack.push(condition1);
            }
            //2、给y加满水
            List<Integer> condition2 = Arrays.asList(curX, y);
            if (!set.contains(condition2)) {
                set.add(condition2);
                stack.push(condition2);
            }
            //3、x倒空
            List<Integer> condition3 = Arrays.asList(0, curY);
            if (!set.contains(condition3)) {
                set.add(condition3);
                stack.push(condition3);
            }
            //4、y倒空
            List<Integer> condition4 = Arrays.asList(curX, 0);
            if (!set.contains(condition4)) {
                set.add(condition4);
                stack.push(condition4);
            }
            //5、x倒入y，直到x倒空或者y满
            List<Integer> condition5 = (curX + curY >= y) ?
                    Arrays.asList(curX + curY - y, y) :
                    Arrays.asList(0, curX + curY);
            if (!set.contains(condition5)) {
                set.add(condition5);
                stack.push(condition5);
            }
            //6、y倒入x，直到y倒空或者x满
            List<Integer> condition6 = (curX + curY >= x) ?
                    Arrays.asList(x, curX + curY - x) :
                    Arrays.asList(curX + curY, 0);
            if (!set.contains(condition6)) {
                set.add(condition6);
                stack.push(condition6);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Water water = new Water();
        System.out.println(water.canMeasureWater(3, 5, 4));
        System.out.println(water.canMeasureWater(2, 6, 5));
        System.out.println(water.canMeasureWater(1, 2, 3));


        System.out.println(water.canMeasureWater2(3, 5, 4));
        System.out.println(water.canMeasureWater2(2, 6, 5));
        System.out.println(water.canMeasureWater2(1, 2, 3));
    }
}
