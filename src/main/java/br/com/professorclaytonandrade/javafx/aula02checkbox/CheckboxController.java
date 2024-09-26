package br.com.professorclaytonandrade.javafx.aula02checkbox;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CheckboxController {

    @FXML
    private CheckBox checkBox;
    @FXML
    private PasswordField password;
    @FXML
    private TextField textPassword;

    @FXML
    public void mostrarSenha(){
        if(checkBox.isSelected()){
            textPassword.setText(password.getText());
            textPassword.setVisible(true);
            password.setVisible(false);
            return;
        }
        password.setText(textPassword.getText());
        password.setVisible(true);
        textPassword.setVisible(false);
    }

}
