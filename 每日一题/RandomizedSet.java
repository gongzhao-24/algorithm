/**
* 描述：剑指 Offer II 030. 
* 创建日期：2022年06月07 20:24:
* @author gong zhao 
**/
package 每日一题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：
 * 
 * insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
 * remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
 * getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/FortPu
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class RandomizedSet {
    Random random = new Random();
    Map<Integer, Integer> numToIndex;
    List<Integer> list;
    int size;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        size = 0;
        numToIndex = new HashMap<>();
        list = new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain
     * the specified element.
     */
    public boolean insert(int val) {
        if (numToIndex.containsKey(val)) {
            return false;
        }
        list.add(val);
        numToIndex.put(val, size);
        size++;
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified
     * element.
     */
    public boolean remove(int val) {
        if (!numToIndex.containsKey(val)) {
            return false;
        }
        int index = numToIndex.get(val);
        int last = list.get(size - 1);
        numToIndex.put(last, index);
        numToIndex.remove(val);
        if (size != 1) {
            list.set(index, last);
        }
        size--;
        list.remove(size);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int index = random.nextInt(size);
        return list.get(index);
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.getRandom());
    }

}
