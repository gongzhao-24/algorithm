/**
* 描述：901. 股票价格跨度
* 创建日期：2022年10月21 15:49:
* @author gong zhao 
**/
package 单调栈;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StockSpanner {
    /**
     * 每天股票价格的跨度被定义为股票价格小于或者等于几天价格的最大连续天数
     * （从今天开始往回数，包括今天）
     * 就是从当前数往前数，前面有几个数字比当前数字小，感觉是单调栈吧
     * 单调栈中放index，同时也记住当前的位序
     * 
     * 以下面这个数组为例子
     * [100],[80],[60],[70],[60],[75],[85]
     * 
     * 100,stack为空，返回1 [0]
     * 80 ,栈顶是100，比80大，返回 1 [0, 1]
     * 60 ,栈顶事 80.比60大，返回 1 【0， 1， 2】
     * 70，栈顶是 60，推出一个，栈顶变成80，返回2【0， 1， 3】
     * 60，返回1，【0， 1， 3， 4】【100， 80，70，60】
     * 75
     * 
     * 
     * 
     * 
     */
    // 记录 index - number
    Map<Integer, Integer> map;
    int curIndex;
    Stack<Integer> stack;

    public StockSpanner() {
        map = new HashMap<>();
        stack = new Stack<>();
        curIndex = 0;

    }

    public int next(int price) {
        if (stack.isEmpty()) {
            stack.push(curIndex);
            map.put(curIndex, price);
            curIndex++;
            return 1;
        }
        int lastIndex = 0;
        while (!stack.isEmpty()) {
            lastIndex = stack.peek();
            int prePrice = map.get(lastIndex);
            if (prePrice > price) {
                break;
            }
            stack.pop();
            lastIndex = -1;
        }
        stack.push(curIndex);
        map.put(curIndex, price);
        int res = curIndex - lastIndex;
        curIndex++;
        return res;
    }
}
