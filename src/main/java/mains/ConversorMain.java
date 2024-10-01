package mains;

import java.io.IOException;

import mains.application.Application;
import mains.fxml.FXMLLoader;
import mains.scene.Parent;
import mains.scene.Scene;
import mains.stage.Stage;

public class ConversorMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/br/com/professorclaytonandrade/javafx/conversor.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        stage.setTitle("TempMan");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}