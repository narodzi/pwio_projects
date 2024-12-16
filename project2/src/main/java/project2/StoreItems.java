package project2;

import java.util.ArrayList;
import java.util.List;

public class StoreItems {
    private List<StoreItem> items;

    public StoreItems() {
        items = new ArrayList<>();
    }

    public StoreItems(List<StoreItem> items) {
        this.items = items;
    }

    public StoreItems(int[] weights, int[] values) {
        this.items = new ArrayList<StoreItem>();
        for (int i = 0; i < weights.length; i++) {
            items.add(new StoreItem(weights[i], values[i]));
        }
    }

    public List<StoreItem> getItems() {
        return items;
    }

    public void addItem(StoreItem item) {
        items.add(item);
    }

    public int size(){
        return items.size();
    }

    public StoreItem get(int n){
        return items.get(n);
    }

    public StoreItem getLast(){
        return items.get(size()-1);
    }

    public int getCumulativePrice(){
        int total = 0;
        for (StoreItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (StoreItem item : items) {
            sb.append(item.toString());
            sb.append(", ");
        }
        return sb.toString();
    }
}
