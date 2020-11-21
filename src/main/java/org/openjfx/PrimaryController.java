package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dnd.combat.Combat;
import dnd.dice.Dice;
import dnd.dice.RandomNumberGenerator;
import dnd.events.RandomEventGenerator;
import javafx.fxml.FXML;

public class PrimaryController extends App {

    RandomEventGenerator eventGenerator= new RandomEventGenerator(player);
    RandomNumberGenerator numberGenerator = new RandomNumberGenerator();
    Dice dice = new Dice();

    @FXML
    private void initCombat() throws IOException {
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

    @FXML
    private void getEvent() throws IOException {
        App.setRoot("event");
    }

    /** Advance. Either receive a combat encounter (40% chance) or a random event. **/
    @FXML
    private void venture() throws IOException{
        if (eventGenerator.checkForCombat())
            App.setRoot("combat");
        else
            App.setRoot("event");
    }
}
