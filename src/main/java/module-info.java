module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires grpc.api;
    requires grpc.stub;
    requires grpc.protobuf;
    requires com.google.protobuf;
    requires com.google.common;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}