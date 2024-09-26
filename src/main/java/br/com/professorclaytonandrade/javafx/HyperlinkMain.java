package br.com.professorclaytonandrade.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HyperlinkMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("hyperlink-webview.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Hyperlink | WebView");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}