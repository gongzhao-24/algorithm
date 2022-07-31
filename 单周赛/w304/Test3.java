/**
* 描述：
* 创建日期：2022年07月31 10:27:
* @author gong zhao 
**/
package 单周赛.w304;

import java.util.Arrays;

public class Test3 {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        int[] d1 = new int[n];
        int[] d2 = new int[n];

        Arrays.fill(d1, -1);
        Arrays.fill(d2, -1);

        d1[node1] = 0;
        d2[node2] = 0;

        int d = 1;
        int current = node1;
        while (edges[current] != -1 && d1[edges[current]] == -1) {
            current = edges[current];
            d1[current] = d;
            d++;
        }

        d = 1;
        current = node2;
        while (edges[current] != -1 && d2[edges[current]] == -1) {
            current = edges[current];
            d2[current] = d;
            d++;
        }
        int min = Integer.MAX_VALUE;
        int node = -1;
        for (int i = 0; i < n; i++) {
            if (d1[i] != -1 && d2[i] != -1) {
                if (min > Math.max(d1[i], d2[i]) || (min == Math.max(d1[i], d2[i]) && i < node)) {
                    node = i;
                    min = Math.max(d1[i], d2[i]);
                }
            }
        }
        return node;
    }

    public static void main(String[] args) {
        int[] edges = new int[] { 4, 4, 8, -1, 9, 8, 4, 4, 1, 1 };
        int node1 = 5;
        int node2 = 6;

        Test3 test3 = new Test3();
        System.out.println(test3.closestMeetingNode(edges, node1, node2));
    }

}
