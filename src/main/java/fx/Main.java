package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = initScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Scene initScene() {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/main.fxml"));
        Parent parent = null;

        try {
            parent = loader.load();
        } catch (IOException e) {
            System.out.println("file not found!");
            e.printStackTrace();
        }

        if (parent != null) {
            return new Scene(parent);
        }else return null;

    }
}
