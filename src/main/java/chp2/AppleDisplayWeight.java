package chp2;

/**
 * Created by lex on 7/19/18.
 */
public class AppleDisplayWeight implements AppleFormatter {
    public String display(Apple apple) {
        return "" + apple.getWeight();
    }
}
