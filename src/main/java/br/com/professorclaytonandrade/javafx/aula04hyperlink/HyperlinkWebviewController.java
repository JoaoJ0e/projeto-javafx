package br.com.professorclaytonandrade.javafx.aula04hyperlink;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.web.WebView;

import java.io.IOException;

public class HyperlinkWebviewController {

    @FXML
    private WebView webView;

    @FXML
    void acessarSite() {
        String url = "https://professorclaytonandrade.com.br/";
        webView.getEngine().load(url);
    }

    @FXML
    void acessarYoutube() {
        try {
            String url = "https://www.youtube.com/?app=desktop&gl=BR&hl=PT";
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start chrome --guest " + url});
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
