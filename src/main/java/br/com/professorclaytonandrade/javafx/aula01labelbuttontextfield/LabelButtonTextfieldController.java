package br.com.professorclaytonandrade.javafx.aula01labelbuttontextfield;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LabelButtonTextfieldController {
    @FXML
    private Label labelResultado;
    @FXML
    private TextField textFieldNome;

    @FXML
    public void clicarBotaoAtualizarResultado(){
        labelResultado.setText(textFieldNome.getText());
    }


}
