package chp11;


public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double calculatePrice(String product) {
        delay();
        return Math.random()*product.charAt(0) + product.charAt(1);
    }

    private void delay() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
