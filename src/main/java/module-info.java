module br.com.professorclaytonandrade.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.professorclaytonandrade.javafx to javafx.fxml;
    exports br.com.professorclaytonandrade.javafx;
    exports br.com.professorclaytonandrade.javafx.aula01labelbuttontextfield;
    opens br.com.professorclaytonandrade.javafx.aula01labelbuttontextfield to javafx.fxml;
}