package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dnd.characters.Character;
import dnd.characters.CharacterFactory;
import dnd.characters.UserNameSingleton;
import dnd.combat.Combat;
import dnd.dice.Dice;
import dnd.dice.RandomNumberGenerator;
import dnd.events.RandomEventGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Game extends App{

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
        CharacterFactory c = new CharacterFactory();
        //be default, the character first created will always be a human with the same name as the user
        player = c.createCharacter("Human", UserNameSingleton.getInstance().getUserName().getText());

        App.setRoot("character");
    }

    @FXML
    private void getEvent() throws IOException {
        App.setRoot("event");
    }

    /** Advance. Decide what type of event to do next.

     Combat (25% chance) = 1
     Quiz (20% chance) = 2
     Standard event (55% chance) = 0 **/
    @FXML
    private void venture() throws IOException{
        if (eventGenerator.decideEventType() == 1)
            App.setRoot("combat");
        else
            App.setRoot("event");
    }

    @FXML
    private void loadQuiz() throws IOException {
        App.setRoot("quiz");
    }

    @FXML
    private void customizeCharacter() throws IOException {
        App.setRoot("character");
    }
}
