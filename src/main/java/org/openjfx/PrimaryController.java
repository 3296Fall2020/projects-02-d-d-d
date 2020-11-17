package org.openjfx;

import java.io.IOException;

import dnd.combat.Combat;
import javafx.fxml.FXML;

public class PrimaryController extends App {

    @FXML
    private void initCombat() throws IOException {
        combat = new Combat(player);
        App.setRoot("combat");
    }

    @FXML
    private void newGame() throws IOException {
        //make new game here & do character creation
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
