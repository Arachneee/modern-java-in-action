package chapter16;

public class Shop {
    private String name;
    private int price;
    public int getPrice() {
        return price;
    }


    public Shop(String name) {
        this.name = name;
        price = name.length()*100;
    }


    public String getName() {
        return name;
    }
}
