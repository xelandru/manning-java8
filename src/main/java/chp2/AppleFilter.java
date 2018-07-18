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
}
