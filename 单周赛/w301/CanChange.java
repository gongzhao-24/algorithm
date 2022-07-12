/**
* 描述：
* 创建日期：2022年07月10 10:46:
* @author gong zhao 
**/
package 单周赛.w301;

import java.util.ArrayList;

public class CanChange {
    public boolean canChange(String start, String target) {
        /**
         * L能不能移动只跟r有关，r能不能移动只跟L有关
         */
        int coutL = 0;
        int coutR = 0;
        for (char c : target.toCharArray()) {
            if (c == 'L') {
                coutL++;
            }
            if (c == 'R') {
                coutR++;
            }
        }
        int[] LArray = new int[coutL];
        int[] RArray = new int[coutR];
        int lIndex = 0;
        int rIndex = 0;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (c == 'L') {
                LArray[lIndex++] = i;
            }
            if (c == 'R') {
                RArray[rIndex++] = i;
            }
        }

        int startCoutL = 0;
        int startCoutR = 0;
        for (char c : start.toCharArray()) {
            if (c == 'L') {
                startCoutL++;
            }
            if (c == 'R') {
                startCoutR++;
            }
        }
        if (coutL != startCoutL || coutR != startCoutR) {
            return false;
        }
        for (int i = 0, j = 0; i < start.length() && j < target.length();) {
            if (start.charAt(i) == '_' && target.charAt(j) != '_') {
                i++;
            } else if (target.charAt(j) == '_' && start.charAt(i) != '_') {
                j++;
            } else {
                if (start.charAt(i) == target.charAt(j)) {
                    i++;
                    j++;
                } else {
                    return false;
                }
            }
        }

        int[] startLArray = new int[startCoutL];
        int[] startRArray = new int[startCoutR];
        int startlIndex = 0;
        int startrIndex = 0;
        for (int i = 0; i < start.length(); i++) {
            char c = start.charAt(i);
            if (c == 'L') {
                startLArray[startlIndex++] = i;
            }
            if (c == 'R') {
                startRArray[startrIndex++] = i;
            }
        }
        ArrayList<Character> list = new ArrayList<>();
        for (char c : start.toCharArray()) {
            if (c == 'L' || c == 'R') {
                list.add(c);
            }
        }
        int nextCharIndex = 0;
        int charLCount = 0;
        int charRCount = 0;
        char nextChar = list.get(nextCharIndex++);
        int left = 0;
        int right = 0;
        for (int i = 0; i < start.length(); i++) {
            if ((nextChar == 'L' && start.charAt(i) == 'R') || (nextChar == 'R' && start.charAt(i) == 'L')) {
                right = i - 1;
                if (nextChar == 'L') {
                    int rangLeft = left;
                    int rangeRight = startLArray[charLCount] - 1;

                    if (LArray[charLCount] < rangLeft || LArray[charLCount] > rangeRight) {
                        return false;
                    }
                } else if (nextChar == 'R') {
                    int rangeRight = right;
                    int rangLeft = startRArray[charRCount] + 1;
                    if (RArray[charRCount] < rangLeft || RArray[charRCount] > rangeRight) {
                        return false;
                    }
                }
                left = i + 1;
                nextChar = list.get(nextCharIndex++);
            }
            right = i;
        }
        if (nextChar == 'L') {
            int rangLeft = left;
            int rangeRight = startLArray[charLCount] - 1;

            if (LArray[charLCount] < rangLeft || LArray[charLCount] > rangeRight) {
                return false;
            }
        } else if (nextChar == 'R') {
            int rangeRight = right;
            int rangLeft = startRArray[charRCount] + 1;
            if (RArray[charRCount] < rangLeft || RArray[charRCount] > rangeRight) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        CanChange canChange = new CanChange();
        String start = "_L__R__RL";
        String target = "L_____RLR";
        System.out.println(canChange.canChange(start, target));
    }
}
