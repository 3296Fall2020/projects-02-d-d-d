package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dnd.characters.Character;
import dnd.characters.UserNameSingleton;
import dnd.dice.Dice;
import dnd.dice.RandomNumberGenerator;
import dnd.events.RandomEventGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.*;
import javafx.scene.text.Text;

public class Game extends App implements Initializable {

    private String gameName = UserNameSingleton.getInstance().getUserName().getText();

    public Text saveMessage;
    public Text characterStats;
    public Text idleDescription;

    RandomEventGenerator eventGenerator = new RandomEventGenerator(player);
    RandomNumberGenerator randomizer = new RandomNumberGenerator();

    public Button saveButton;
    public Button menuButton;
    public Button ventureButton;
    public Button initCombatButton;

    /** Advance. Decide what type of event to do next.

     Combat (25% chance) = 1
     Quiz (20% chance) = 2
     Standard event (55% chance) = 0 **/
    @FXML
    private void venture() throws IOException{
        if (eventGenerator.decideEventType() == 1)
            App.setRoot("combat");
        else if (eventGenerator.decideEventType() == 2)
            App.setRoot("quizevent");
        else
            App.setRoot("standardevent");
    }

    //Initiate combat
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

    //Return to the main menu
    @FXML
    private void returnToMenu() throws IOException {
        App.setRoot("mainmenu");
    }

    //Show player stats
    private void showStats(){
        String stats = "Name: " + player.getName() +
                "\nRace & class: " + player.getRace() + " " + player.getClassMembership() +
                "\nLevel: " + player.getLevel() + " (" + player.getXP() + " XP)" +
                "\nHP: " + player.getHitPoints();
        characterStats.setText(stats);
    }

    private void showIdleDescription(){
        String desc = "You stand idly by. ";
        String[] strings = {"You feel a little bored.",
                            "There's a whole day ahead of you! What should you do next?",
                            "You feel like doing some adventuring.",
                            "Who knows what you might run into today?",
                            "Maybe you can find some opponents to fight...",
                            "You yawn.",
                            "Is the day over yet?",
                            "Why does this day feel so long?",
                            "You're starting to feel more energetic.",
                            "You're starting to feel tired."};
        desc += strings[randomizer.randomIntInRange(0, strings.length - 1)];
        updateIdleDescription(desc);
    }

    private void updateIdleDescription(String text){
        idleDescription.setText(text);
    }

    @FXML
    private void gameOver() throws IOException {
        updateIdleDescription("Oh no, you've dropped below 0 HP! Your journey has come to an end... \n\n" +
                "Start a new game, or return to the main menu?");

        initCombatButton.setVisible(false);
        ventureButton.setVisible(false);

        saveButton.setText("New Game");
        menuButton.setText("Main Menu");

        //repurpose saveButton to be a "new game" button
        saveButton.setOnAction(value ->  {
            try {
                App.setRoot("character");
            } catch (IOException e) {
                System.out.println("Error: Couldn't start a new game");
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showStats();
        if (player.getHitPoints() <= 0){
            try {
                gameOver();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            showIdleDescription();
        }
    }
}
