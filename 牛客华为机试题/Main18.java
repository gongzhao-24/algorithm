/**
* 描述：HJ18 识别有效的IP地址和掩码并进行分类统计
* 创建日期：2022年07月21 19:16:
* @author gong zhao 
**/
package 牛客华为机试题;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main18 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList();
        while (in.hasNextLine()) {
            list.add(in.nextLine());
        }
        int[] ans = new int[7];
        for (String str : list) {
            String[] array = str.split("-");
        }

    }

    // 判断子网掩码是不是非法的
    public boolean checkSubnetMast(String str) {
        String[] array = str.split(".");
        if (array.length != 4) {
            return false;
        }
        // 在这个数之前，有1存在，即有不是全0的数字
        boolean flag = false;
        for (int i = array.length - 1; i >= 0; i--) {
            int num = Integer.parseInt(array[i]);
            // 判断num是不是全1
            if ((num & (num + 1)) == 0) {
                // 说明num是全1
                flag = true;
            } else {
                if (flag) {
                    return false;
                }
            }
        }
        return true;
    }

    // 判断ip是不是非法的 A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数
    public int checkIp(String str) {
        String[] array = str.split(".");
        if (array.length != 4) {
            return 6;
        }
        int[] nums = new int[4];
        for (int i = 0; i < 4; i++) {

        }

    }
}
