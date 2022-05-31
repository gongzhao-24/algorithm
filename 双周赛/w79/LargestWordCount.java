/**
* 描述：
* 创建日期：2022年05月28 22:38:
* @author gong zhao 
**/
package BiweeklyContest79;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LargestWordCount {
    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < messages.length; i++) {
            map.put(senders[i], map.getOrDefault(senders[i], 0) + countWord(messages[i]));
        }
        int maxCount = 0;
        List<String> list = new ArrayList();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            return (o1 + o2).compareTo(o2 + o1);
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            maxCount = Math.max(maxCount, entry.getValue());
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxCount) {
                priorityQueue.offer(entry.getKey());
            }
        }
        return priorityQueue.poll();

    }

    public int countWord(String message) {
        String[] words = message.split(" ");
        return words.length;
    }
}
