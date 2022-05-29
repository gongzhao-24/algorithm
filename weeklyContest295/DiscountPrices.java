/**
* 描述：
* 创建日期：2022年05月29 10:35:
* @author gong zhao 
**/
package weeklyContest295;

import java.math.BigDecimal;

public class DiscountPrices {
    public String discountPrices(String sentence, int discount) {
        String[] sentenceArray = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str : sentenceArray) {
            sb.append(" ");
            sb.append(checkAndConvert(str, discount));
        }
        return sb.toString().trim();
    }

    public String checkAndConvert(String string, int discount) {
        if (string.charAt(0) != '$' || string.length() == 1) {
            return string;
        }
        // 判断是不是都是数字
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) < '0' || string.charAt(i) > '9') {
                return string;
            }
        }
        // 这里进行转换
        Double price = Double.parseDouble(string.substring(1));

        price = price * (100 - discount) / 100;
        return "$" + String.format("%.2f",price);

    }

    public static void main(String[] args) {
        DiscountPrices discountPrices = new DiscountPrices();
        String sentence = "there are $1 $2 and 5$ candies in the shop";
        System.out.println(discountPrices.discountPrices(sentence, 50));
    }
}
