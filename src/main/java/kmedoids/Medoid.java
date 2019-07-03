package kmedoids;

import java.util.ArrayList;
import java.util.List;

public class Medoid {

    private List<Item> items = new ArrayList<>();
    private Item centerOfMedoid;
    private double cost;

    Medoid(Item centerOfMedoid, double cost) {
        this.centerOfMedoid = centerOfMedoid;
        this.cost = cost;
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getCenterOfMedoid() {
        return centerOfMedoid;
    }

    double getCost() {
        return cost;
    }

    void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "\nMedoid{" +
                "items=" + items +
                ", centerOfMedoid=" + centerOfMedoid +
                ", cost=" + cost +
                '}';
    }
}
