module com.example.fishing {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.xml;
    requires rome;
    requires java.desktop;

    opens com.example.fishing to javafx.fxml;
    exports com.example.fishing;
}