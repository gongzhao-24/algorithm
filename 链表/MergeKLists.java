/**
* 描述：
* 创建日期：2022年10月14 16:42:
* @author gong zhao 
**/
package 链表;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.ListNode;

public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        /**
         * 有10000个链表，每个链表的长度为500，
         * 最朴素的做法，每次将所有的链表都对比一下，然后取最小的那个，然后移动index，
         * 依次直到全部取完为止,这种方法的时间复杂度是
         * 每次遍历 10000 * 10000 * 500，感觉过不了。
         */
        List<Integer> list = new ArrayList<>();
        for (ListNode listNode : lists) {
            while (listNode != null) {
                list.add(listNode.val);
                listNode = listNode.next;
            }
        }
        Collections.sort(list);
        ListNode virtualHead = new ListNode(-1);
        ListNode node = virtualHead;

        for (int num : list) {
            ListNode listNode = new ListNode(num);
            node.next = listNode;
            node = node.next;
        }
        return virtualHead.next;
    }
}

