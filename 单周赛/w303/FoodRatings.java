/**
* 描述：
* 创建日期：2022年07月24 10:37:
* @author gong zhao 
**/
package 单周赛.w303;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FoodRatings {
    String[] foods;
    String[] cuisines;
    int[] ratings;
    // 表示某种烹饪方式代表的食物评分
    Map<String, TreeSet<String>> cuisine2Rating;
    Map<String, Integer> food2Index;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        this.foods = foods;
        this.cuisines = cuisines;
        this.ratings = ratings;
        cuisine2Rating = new HashMap<String, TreeSet<String>>();
        food2Index = new HashMap<>();
        for (int i = 0; i < foods.length; i++) {
            food2Index.put(foods[i], i);
        }
        for (int i = 0; i < cuisines.length; i++) {
            if (cuisine2Rating.getOrDefault(cuisines[i], null) == null) {

                TreeSet<String> treeSet = new TreeSet<>((o1, o2) -> {
                    int o1Index = food2Index.get(o1);
                    int o2Index = food2Index.get(o2);
                    if (o1Index == o2Index) {
                        return 0;
                    }
                    if (ratings[o1Index] == ratings[o2Index]) {
                        return o2.compareTo(o1);
                    } else {
                        return ratings[o1Index] - ratings[o2Index];
                    }
                });

                cuisine2Rating.put(cuisines[i], treeSet);
                cuisine2Rating.get(cuisines[i]).add(foods[i]);
            } else {
                cuisine2Rating.get(cuisines[i]).add(foods[i]);
            }
        }
    }

    public void changeRating(String food, int newRating) {
        // 修改某种食物的评分
        int index = food2Index.get(food);
        String cui = cuisines[index];

        cuisine2Rating.get(cui).remove(food);
        ratings[index] = newRating;
        cuisine2Rating.get(cui).add(food);
    }

    public String highestRated(String cuisine) {
        return cuisine2Rating.get(cuisine).last();
    }

    public static void main(String[] args) {
        String[] foods = new String[] { "czopaaeyl", "lxoozsbh", "kbaxapl" };
        String[] cuisines = new String[] { "dmnuqeatj", "dmnuqeatj", "dmnuqeatj" };
        int[] ratings = new int[] { 11, 2, 15 };
        FoodRatings foodRatings = new FoodRatings(foods, cuisines, ratings);

        foodRatings.changeRating("czopaaeyl", 12);

        System.out.println(foodRatings.highestRated("dmnuqeatj"));

        foodRatings.changeRating("kbaxapl", 8);
        foodRatings.changeRating("lxoozsbh", 5);

        System.out.println(foodRatings.highestRated("dmnuqeatj"));
        System.out.println();

    }
}
