package fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import kmedoids.Kmedoids;
import kmedoids.Medoid;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ScatterChart<Number, Number> sc;

    public void initialize(URL location, ResourceBundle resources) {

        Kmedoids kmedoids = new Kmedoids();
        List<Medoid> medoids = kmedoids.run();

        sc.setTitle("KMedoids");

        XYChart.Series centers = new XYChart.Series();
        centers.setName("center");

        for (Medoid medoid :
                medoids) {
            for (int i = 0; i < medoid.getCenterOfMedoid().getFeatures().size(); i++) {
                centers.getData().add(new XYChart.Data(medoid.getCenterOfMedoid().getFeatures().get(i++),
                        medoid.getCenterOfMedoid().getFeatures().get(i)));
            }
        }

        XYChart.Series series;
        for (int i = 0; i < medoids.size(); i++) {
            series = new XYChart.Series();
            for (int j = 0; j < medoids.get(i).getItems().size(); j++) {
                if (medoids.get(i).getItems().get(j).isMedoids())
                    continue;
                else
                    series.getData().add(new XYChart.Data(medoids.get(i).getItems().get(j).getFeatures().get(0),
                            medoids.get(i).getItems().get(j).getFeatures().get(1)));
            }
            sc.getData().addAll(series);
        }

        sc.getData().addAll(centers);
    }

}
