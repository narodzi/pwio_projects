package project2;

import java.util.Collection;

public class StoreItem {
    private int weight;
    private int price;

    public StoreItem(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Przedmiot [weight=" + weight + ", price=" + price + "]";
    }
}
