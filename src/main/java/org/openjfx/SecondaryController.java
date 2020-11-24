package org.openjfx;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController extends App {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("combat");
    }
}