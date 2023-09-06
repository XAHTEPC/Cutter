module com.example.cutter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cutter to javafx.fxml;
    exports com.example.cutter;
}