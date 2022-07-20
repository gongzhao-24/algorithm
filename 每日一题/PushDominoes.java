/**
* 描述：
* 创建日期：2022年07月19 10:15:
* @author gong zhao 
**/
package 每日一题;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class PushDominoes {
    public String pushDominoes(String dominoes) {
        if (dominoes.length() <= 1) {
            return dominoes;
        }
        char[] dominoesArray = dominoes.toCharArray();
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < dominoes.length(); i++) {
            if (dominoes.charAt(i) != '.') {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            Map<Integer, Character> map = new HashMap<>();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                if (dominoesArray[index] == 'L') {
                    if (index == 0 || dominoesArray[index - 1] == 'L' || dominoesArray[index - 1] == 'R') {
                        continue;
                    }
                    Character c = map.get(index - 1);
                    if (c != null && c == 'R') {
                        map.remove(index - 1);
                    } else {
                        map.put(index - 1, 'L');
                    }

                } else if (dominoesArray[index] == 'R') {
                    if (index == dominoes.length() - 1 || dominoesArray[index + 1] == 'R'
                            || dominoesArray[index + 1] == 'L') {
                        continue;
                    }
                    Character c = map.get(index + 1);
                    if (c != null && c == 'L') {
                        map.remove(index + 1);
                    } else {
                        map.put(index + 1, 'R');
                    }
                }

            }

            for (Map.Entry<Integer, Character> entry : map.entrySet()) {
                queue.add(entry.getKey());
                dominoesArray[entry.getKey()] = entry.getValue();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : dominoesArray) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PushDominoes pushDominoes = new PushDominoes();
        System.out.println(pushDominoes.pushDominoes(".L.R...LR..L.."));
    }
}
