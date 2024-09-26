package br.com.professorclaytonandrade.javafx.aula03radiobuttontogglegroup;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class RadiobuttonTogglegroupController {
    @FXML
    private ToggleGroup finds;
    @FXML
    private Label lbResultado;
    @FXML
    private RadioButton rbAcampar;
    @FXML
    private RadioButton rbNetflix;
    @FXML
    private RadioButton rbPraia;
    @FXML
    void opcaoFindSemana() {
        if (rbPraia.isSelected()){
            lbResultado.setText("Vamos à " + rbPraia.getText());
        } else if (rbNetflix.isSelected()) {
            lbResultado.setText("Vamos assistir " + rbNetflix.getText());
        } else if (rbAcampar.isSelected()) {
            lbResultado.setText("Vamos " + rbAcampar.getText());
        }
    }
}
