module com.se233.chapter4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;

    opens com.se233.chapter4 to javafx.fxml;
    exports com.se233.chapter4;
}