package kmedoids;

import java.util.List;

public class Item {

    private List<Double> features;
    private String productName;
    private boolean isMedoids;

    Item(List<Double> features, boolean isMedoids, String productName) {
        this.features = features;
        this.isMedoids = isMedoids;
        this.productName = productName;
    }

    double computeDissimilarity(Item medoid) {
        double sum = 0;
        for (int i = 0; i < this.features.size(); i++) {
            sum += Math.pow(features.get(i) - medoid.getFeatures().get(i), 2);
        }
        return Math.sqrt(sum);
    }

    public List<Double> getFeatures() {
        return features;
    }

    public boolean isMedoids() {
        return isMedoids;
    }

    void setMedoids(boolean medoids) {
        isMedoids = medoids;
    }


    @Override
    public String toString() {
        return "Item{" +
                "productName='" + productName + '\'' +
                '}';
    }
}
