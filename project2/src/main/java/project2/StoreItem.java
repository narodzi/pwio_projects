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

    public static int getCumulativeValue(Collection<StoreItem> items) {
        int sum = 0;
        for (StoreItem item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    public static String itemsToString(Collection<StoreItem> items) {
        StringBuilder sb = new StringBuilder();
        for (StoreItem item : items) {
            sb.append(item.toString());
            sb.append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}
