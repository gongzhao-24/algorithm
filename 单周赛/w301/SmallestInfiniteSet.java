/**
* 描述：
* 创建日期：2022年07月10 10:38:
* @author gong zhao 
**/
package 单周赛.w301;

import java.util.HashSet;
import java.util.Set;

public class SmallestInfiniteSet {
    // 记录一个已经被移除的数字
    // 记录最小的数字
    Set<Integer> set;
    int smallest;

    public SmallestInfiniteSet() {
        set = new HashSet<>();
        smallest = 1;
    }

    public int popSmallest() {
        set.add(smallest);
        int nextSmallest = smallest + 1;
        while (set.contains(nextSmallest)) {
            nextSmallest++;
        }
        int temp = smallest;
        smallest = nextSmallest;
        return temp;
    }

    public void addBack(int num) {
        if (set.contains(num)) {
            set.remove(num);
            smallest = Math.min(smallest, num);
        }
    }

    public static void main(String[] args) {
        SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
        smallestInfiniteSet.addBack(2);
        System.out.println(smallestInfiniteSet.popSmallest());
        System.out.println(smallestInfiniteSet.popSmallest());
        System.out.println(smallestInfiniteSet.popSmallest());
        smallestInfiniteSet.addBack(1);
        System.out.println(smallestInfiniteSet.popSmallest());
        System.out.println(smallestInfiniteSet.popSmallest());
        System.out.println(smallestInfiniteSet.popSmallest());
    }
}
