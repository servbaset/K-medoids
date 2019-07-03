package kmedoids;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Kmedoids {


    public List<Medoid> run() {
        List<Item> items;
        items = fileToListOfItems(2, 3);
        // System.out.println(items);

        int k = 5;

        Random random = new Random(0);
        List<Medoid> finalMedoids = new ArrayList<>();
        System.out.println("first step:");
        firstStep(items, k, random, finalMedoids);
        //first cluster
        secondStep(items, finalMedoids);
        System.out.println("compute:");
        while (true) {
            List<Medoid> medoids = new ArrayList<>();

            //creat medoids an
            // d set center
            firstStep(items, k, random, medoids);
            //first cluster
            secondStep(items, medoids);

            double sum1 = 0, sumfinal = 0;
            for (int i = 0; i < medoids.size(); i++) {
                sum1 += medoids.get(i).getCost();
            }
            for (int i = 0; i < finalMedoids.size(); i++) {
                sumfinal += finalMedoids.get(i).getCost();
            }
            if (sum1 >= sumfinal) {
                break;
            }
            finalMedoids = medoids;
            finalMedoidsSetFalse(medoids);
        }

        System.out.println("final clustring :" + finalMedoids);
        return finalMedoids;
    }

    private void finalMedoidsSetFalse(List<Medoid> finalMedoids) {
        for (Medoid medoid :
                finalMedoids) {
            medoid.getCenterOfMedoid().setMedoids(false);
        }
    }


    private void firstStep(List<Item> items, int k, Random random, List<Medoid> medoids) {
        for (int i = 0; i < k; i++) {
            int index = random.nextInt(items.size() - 1);
            Item item = items.get(index);
            if (item.isMedoids()) {
                i--;
            } else {
                item.setMedoids(true);
                Medoid medoid = new Medoid(item, 0);
                medoids.add(medoid);
            }
        }

        //System.out.println(medoids);
    }

    private void secondStep(List<Item> items, List<Medoid> medoids) {

        for (int i = 0; i < items.size(); i++) {
            double minDistance = Double.MAX_VALUE;
            int indexMin = 0;
            for (int j = 0; j < medoids.size(); j++) {
                double distance = items.get(i).computeDissimilarity(medoids.get(j).getCenterOfMedoid());
                if (distance < minDistance) {
                    minDistance = distance;
                    indexMin = j;
                }
            }
            medoids.get(indexMin).getItems().add(items.get(i));
            double oldCost = medoids.get(indexMin).getCost();
            medoids.get(indexMin).setCost(oldCost + minDistance);
        }

        System.out.println(medoids);
    }

    private List<Item> fileToListOfItems(int firstC, int secondC) {

        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        TsvParser parser = new TsvParser(settings);

        List<Item> itemList = new ArrayList<>();

        List<String[]> allRows = parser.parseAll(this.getReader("/home/jahn-shor/IdeaProjects/Kmedoids/src/main/resources/file/outNumeric.tsv"));
        int i = -1;
        for (String[] columns : allRows) {
            i++;
            if (i == 0)
                continue;

            List<Double> features = Arrays.asList(Double.valueOf(columns[firstC]),
                    Double.valueOf(columns[secondC])
            );

            Item item = new Item(features, false, columns[4]);
            itemList.add(item);
        }
        return itemList;
    }

    private Reader getReader(String relativePath) {
        try {
            return new InputStreamReader(new FileInputStream(relativePath), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
