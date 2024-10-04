module com.example.template {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.unsupported.desktop;


    opens com.example.template to javafx.fxml;
    exports com.example.template;
}