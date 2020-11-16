module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.protobuf;
    requires com.google.common;
    requires grpc.api;
    requires grpc.stub;
    requires grpc.protobuf;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;


    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}