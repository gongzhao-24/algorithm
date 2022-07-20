/**
* 描述：
* 创建日期：2022年07月16 15:48:
* @author gong zhao 
**/
package 单周赛.w287;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindWinners {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> loseGame = new HashMap();
        Set<Integer> participate = new HashSet();
        for (int[] matche : matches) {
            loseGame.put(matche[1], loseGame.getOrDefault(matche[1], 0) + 1);
            participate.add(matche[0]);
            participate.add(matche[1]);
        }
        List<Integer> noLose = new ArrayList<>();
        List<Integer> loseOne = new ArrayList<>();
        for (int i : participate) {
            int lose = loseGame.getOrDefault(i, 0);
            if (lose == 0) {
                noLose.add(i);
            } else if (lose == 1) {
                loseOne.add(i);
            }
        }
        Collections.sort(noLose);
        Collections.sort(loseOne);
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(noLose);
        ans.add(loseOne);
        return ans;
    }
}
