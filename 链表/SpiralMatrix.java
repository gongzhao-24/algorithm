/**
* 描述：
* 创建日期：2022年07月03 10:46:
* @author gong zhao 
**/
package 链表;

import java.util.Arrays;

import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

public class SpiralMatrix extends AbstractBaseClass {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] matrix = new int[n][n];
        for (int[] array : matrix) {
            Arrays.fill(array, -1);
        }
        boolean right = true;
        boolean left = false;
        boolean down = false;
        boolean up = false;

        int startX = 0;
        int startY = 0;
        while (head != null) {
            if (right) {
                matrix[startX][startY] = head.val;
                head = head.next;
                if (startY == n - 1 || matrix[startX][startY + 1] != -1) {
                    startX++;
                    right = false;
                    down = true;
                } else {
                    startY++;
                }

            } else if (down) {
                if (startX == m - 1 || matrix[startX + 1][startY] != -1) {
                    startY--;
                    left = true;
                    down = false;
                } else {
                    startX++;
                }

            } else if (left) {
                if (startY == 0 || matrix[startX][startY - 1] != -1) {
                    startX--;
                    up = true;
                    left = false;
                } else {
                    startY--;
                }

            } else if (up) {
                if (startX == 0 || matrix[startX - 1][startY] != -1) {
                    startY++;
                    right = true;
                    up = false;
                } else {
                    startX--;
                }
            }
            System.out.println("startX : " + startX + ", startY:" + startY);
        }
        return matrix;
    }
}
