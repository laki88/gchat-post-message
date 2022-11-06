module org.laki.gchat {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires com.google.gson;

    opens org.laki.gchat to javafx.fxml;
    exports org.laki.gchat;
}