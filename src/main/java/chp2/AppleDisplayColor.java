package chp2;


public class AppleDisplayColor implements AppleFormatter {
    public String display(Apple apple) {
        return apple.getColor();
    }
}
