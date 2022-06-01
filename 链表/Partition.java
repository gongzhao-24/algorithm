/**
* 描述：
* 创建日期：2022年05月31 23:39:
* @author gong zhao 
**/
package 链表;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Partition extends AbstractBaseClass {

    public ListNode partition(ListNode head, int x) {
        //
        ListNode virtualLess = new ListNode(-1);
        ListNode less = virtualLess;
        ListNode virtualMore = new ListNode(-1);
        ListNode more = virtualMore;
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                more.next = head;
                more = more.next;
            }
            head = head.next;
        }
        less.next = null;
        more.next = null;
        less.next = virtualMore.next;
        return virtualLess.next;
    }
}
