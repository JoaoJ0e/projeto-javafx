module br.com.professorclaytonandrade.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires java.net.http;
    requires org.json;
    requires java.rmi;


    opens mains to javafx.fxml;
    exports mains;
    opens conversor to javafx.fxml;
    exports conversor;
}