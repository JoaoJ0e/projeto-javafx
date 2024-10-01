module br.com.professorclaytonandrade.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires java.net.http;
    requires org.json;
    requires java.rmi;


    opens javafx to javafx.fxml;
    exports javafx;
    exports br.com.professorclaytonandrade.javafx.aula01labelbuttontextfield;
    opens br.com.professorclaytonandrade.javafx.aula01labelbuttontextfield to javafx.fxml;
    exports br.com.professorclaytonandrade.javafx.aula02checkbox;
    opens br.com.professorclaytonandrade.javafx.aula02checkbox to javafx.fxml;
    exports br.com.professorclaytonandrade.javafx.aula03radiobuttontogglegroup;
    opens br.com.professorclaytonandrade.javafx.aula03radiobuttontogglegroup to javafx.fxml;
    exports br.com.professorclaytonandrade.javafx.aula04hyperlink;
    opens br.com.professorclaytonandrade.javafx.aula04hyperlink to javafx.fxml;
    opens br.com.professorclaytonandrade.javafx.conversor to javafx.fxml;
    exports br.com.professorclaytonandrade.javafx.conversor;
}