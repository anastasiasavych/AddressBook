module com.example.addressbook {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;

    opens com.example.addressbook to javafx.fxml;
    exports com.example.addressbook;
}