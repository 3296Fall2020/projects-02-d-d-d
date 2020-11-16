package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class CombatEndController extends App {

    public Text outcomeDescription;

    @FXML
    private void finishCombat() throws IOException {
        outcomeDescription.setText(combat.getOutcomeString() + "HHHHHHHHHHHH");
        App.setRoot("primary");
    }

    @FXML
    private void endByFlee(){

    }

    @FXML
    private void endByWin(){

    }

    @FXML
    private void endByLose(){

    }

}
