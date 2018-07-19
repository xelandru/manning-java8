package chp2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 7/18/18.
 */
public class AppleFilter {

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {

        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple))
                result.add(apple);
        }
        return result;
    }

    public static void main(String[] args) {

        List<Apple> redApples = filterApples(new ArrayList<>(), new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
    }
}
