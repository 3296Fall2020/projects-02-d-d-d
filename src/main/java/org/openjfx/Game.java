package org.openjfx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dnd.characters.Character;
import dnd.characters.UserNameSingleton;
import dnd.dice.Dice;
import dnd.dice.RandomNumberGenerator;
import dnd.events.RandomEventGenerator;
import javafx.fxml.FXML;
import javafx.scene.paint.*;
import javafx.scene.text.Text;

public class Game extends App{

    private String gameName = UserNameSingleton.getInstance().getUserName().getText();
    public Text saveMessage;

    RandomEventGenerator eventGenerator= new RandomEventGenerator(player);
    RandomNumberGenerator numberGenerator = new RandomNumberGenerator();
    Dice dice = new Dice();

    @FXML
    private void initCombat() throws IOException {
        App.setRoot("combat");
    }

    //Saves the character to the characterList, which is then saved to the user's game.
    //Currently, the game only supports one character per game, so the save only uses the first character
    //slot and overwrites it with new characters.
    @FXML
    private void saveGame() throws IOException {
        characterList.add(0, player);
        sessionManager.saveGame(gameName, characterList);

        saveMessage.setFill(Color.GREEN);
        saveMessage.setText("Game successfully saved!");
    }

    @FXML
    private void returnToMenu() throws IOException {
        App.setRoot("mainmenu");
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
