/**
* 描述：
* 创建日期：2022年07月09 22:34:
* @author gong zhao 
**/
package 双周赛.w82;

import java.util.Arrays;

/**
 * 排序后，用双指针模拟乘客上车的过程：遍历公交车，找哪些乘客可以上车（先来先上车）。
 * 
 * 模拟结束后：
 * 
 * 如果最后一班公交还有空位，我们可以在发车时到达公交站，如果此刻有人，我们可以顺着他往前找到没人到达的时刻；
 * 如果最后一班公交没有空位，我们可以找到上一个上车的乘客，顺着他往前找到一个没人到达的时刻。
 * 这里可以「插队」的理由是，如果一个乘客上了车，那么他前面的乘客肯定也上了车（因为先来先上车）。
 * 
 */

public class LatestTimeCatchTheBus {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int c = 0;
        int passIndex = 0;
        for (int base : buses) {
            // 跳出循环的只有两种可能性
            // ①：c < 0
            // ②：passIndex == passengers.length
            for (c = capacity; c > 0 && passIndex < passengers.length && passengers[passIndex] <= base;) {
                c--;
                passIndex++;
            }
        }
        passIndex--;
        // c > 0 说明车上还有空座，那么就只需要最后一个人来的时候跟车来的时间相同就行，
        int ans = c > 0 ? buses[buses.length - 1] : passengers[passIndex];
        for (; passIndex >= 0; passIndex--) {
            if (passengers[passIndex] == ans) {
                ans--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LatestTimeCatchTheBus latestTimeCatchTheBus = new LatestTimeCatchTheBus();
        int[] buses = new int[] { 20, 30, 10 };
        int[] passengers = new int[] { 19, 13, 26, 4, 25, 11, 21 };
        int capacity = 2;
        System.out.println(latestTimeCatchTheBus.latestTimeCatchTheBus(buses, passengers, capacity));
    }
}
