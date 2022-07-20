/**
* 描述：
* 创建日期：2022年07月16 15:36:
* @author gong zhao 
**/
package 单周赛.w287;

public class ConvertTime {
    public int convertTime(String current, String correct) {
        String[] currentArray = current.split(":");
        String[] correctArray = correct.split(":");

        int currentHour = Integer.parseInt(currentArray[0]);
        int currentMinute = Integer.parseInt(currentArray[1]);

        int correctHour = Integer.parseInt(correctArray[0]);
        int correctMinute = Integer.parseInt(correctArray[1]);
        int res = 0;
        if (correctHour >= currentHour && correctMinute >= currentMinute) {
            int leftMinute = correctMinute - currentMinute;
            res = correctHour - currentHour;
            while (leftMinute > 0 && leftMinute >= 15) {
                res++;
                leftMinute = leftMinute - 15;
            }
            while (leftMinute > 0 && leftMinute >= 5) {
                res++;
                leftMinute = leftMinute - 5;
            }
            while (leftMinute > 0 && leftMinute >= 1) {
                res++;
                leftMinute = leftMinute - 1;
            }

        } else /* if (correctHour > currentHour && correctMinute < currentMinute) */ {
            res = correctHour - currentHour - 1;
            // 预留了一个小时
            int leftMinute = correctMinute - currentMinute + 60;
            while (leftMinute > 0 && leftMinute >= 15) {
                res++;
                leftMinute = leftMinute - 15;
            }
            while (leftMinute > 0 && leftMinute >= 5) {
                res++;
                leftMinute = leftMinute - 5;
            }
            while (leftMinute > 0 && leftMinute >= 1) {
                res++;
                leftMinute = leftMinute - 1;
            }
        }
        return res;
    }
}
