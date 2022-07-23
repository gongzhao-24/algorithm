/**
* 描述：HJ18 识别有效的IP地址和掩码并进行分类统计
* 创建日期：2022年07月21 19:16:
* @author gong zhao 
**/
package 牛客华为机试题;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
 * 
 * 所有的IP地址划分为 A,B,C,D,E五类
 * 
 * A类地址从1.0.0.0到126.255.255.255;
 * 
 * B类地址从128.0.0.0到191.255.255.255;
 * 
 * C类地址从192.0.0.0到223.255.255.255;
 * 
 * D类地址从224.0.0.0到239.255.255.255；
 * 
 * E类地址从240.0.0.0到255.255.255.255
 * 
 * 
 * 私网IP范围是：
 * 
 * 从10.0.0.0到10.255.255.255
 * 
 * 从172.16.0.0到172.31.255.255
 * 
 * 从192.168.0.0到192.168.255.255
 * 
 * 
 * 子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
 * （注意二进制下全是1或者全是0均为非法子网掩码）
 * 
 * 注意：
 * 1. 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时请忽略
 * 2. 私有IP地址和A,B,C,D,E类地址是不冲突的
 */

public class Main18 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList();
        // while (in.hasNext()) {
        list.add(in.nextLine());
        // }
        int[] ans = new int[8];
        for (String str : list) {
            String[] arrays = str.split("~");
            System.out.println(arrays[0] + "----" + arrays[1]);
            if (arrays.length != 2) {
                ans[5]++;
                continue;
            }
            if (!checkSubnetMast(arrays[1])) {
                ans[5]++;
                continue;
            }
            String[] array = arrays[0].split("\\.");
            if (array.length != 4) {
                ans[5]++;
                continue;
            }
            int[] nums = new int[4];
            for (int i = 0; i < 4; i++) {
                if (array[i].length() == 0) {
                    ans[5]++;
                    continue;
                }
                for (char c : array[i].toCharArray()) {
                    if (c < '0' || c > '9') {
                        ans[5]++;
                        continue;
                    }
                }
                nums[i] = Integer.parseInt(array[i]);
            }
            if (nums[0] >= 1 && nums[0] <= 126) {
                if ((nums[1] >= 0 && nums[1] <= 255) && (nums[2] >= 0 && nums[2] <= 255)
                        && (nums[3] >= 0 && nums[3] <= 255)) {
                    if (nums[0] == 10) {
                        ans[6]++;
                        ans[0]++;
                        continue;
                    } else {
                        ans[0]++;
                        continue;
                    }
                } else {
                    ans[5]++;
                    continue;
                }
                // 从10.0.0.0到10.255.255.255
            } else if (nums[0] >= 128 && nums[0] <= 191) {
                if ((nums[1] >= 0 && nums[1] <= 255) && (nums[2] >= 0 && nums[2] <= 255)
                        && (nums[3] >= 0 && nums[3] <= 255)) {
                    if (nums[0] == 172 && (nums[1] >= 16 && nums[1] <= 31)) {
                        ans[6]++;
                        ans[1]++;
                        continue;
                    } else {
                        ans[1]++;
                        continue;
                    }
                } else {
                    ans[5]++;
                    continue;
                }
                // 从172.16.0.0到172.31.255.255
            } else if (nums[0] >= 192 && nums[0] <= 223) {
                if ((nums[1] >= 0 && nums[1] <= 255) && (nums[2] >= 0 && nums[2] <= 255)
                        && (nums[3] >= 0 && nums[3] <= 255)) {
                    if (nums[0] == 192 && nums[1] == 168) {
                        ans[6]++;
                        ans[2]++;
                        continue;
                    } else {
                        ans[2]++;
                        continue;
                    }
                } else {
                    ans[5]++;
                    continue;
                }
            } else if (nums[0] >= 224 && nums[0] <= 239) {
                if ((nums[1] >= 0 && nums[1] <= 255) && (nums[2] >= 0 && nums[2] <= 255)
                        && (nums[3] >= 0 && nums[3] <= 255)) {
                    ans[3]++;
                    continue;
                } else {
                    ans[5]++;
                    continue;
                }
            } else if (nums[0] >= 240 && nums[0] <= 255) {
                if ((nums[1] >= 0 && nums[1] <= 255) && (nums[2] >= 0 && nums[2] <= 255)
                        && (nums[3] >= 0 && nums[3] <= 255)) {
                    ans[4]++;
                    continue;
                } else {
                    ans[5]++;
                    continue;
                }
            } else if (nums[0] == 0 || nums[0] == 127) {
                // 【0.*.*.*】和【127.*.*.*】 不属于任何类型，忽略
                continue;
            } else {
                // 非法ip
                ans[5]++;
                continue;
            }

        }

        for (int i = 0; i < 7; i++) {
            System.out.print(ans[i]);
            System.out.print(" ");
        }

    }

    // 判断子网掩码是不是非法的
    public static boolean checkSubnetMast(String str) {
        String[] array = str.split("\\.");
        if (array.length != 4) {
            return false;
        }
        // 二进制下，全是1或者全是0的也是非法掩码
        int[] nums = new int[4];
        for (int i = 0; i < 4; i++) {
            if (array[i].length() == 0) {
                return false;
            }
            for (char c : array[i].toCharArray()) {
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            nums[i] = Integer.parseInt(array[i]);
        }
        if (nums[0] == 0 && nums[1] == 0 && nums[2] == 0 && nums[3] == 0) {
            return false;
        }
        if ((nums[0] & (nums[0] - 1)) == 0
                && (nums[1] & (nums[1] - 1)) == 0
                && (nums[2] & (nums[2] - 1)) == 0
                && (nums[3] & (nums[3] - 1)) == 0) {
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
}
