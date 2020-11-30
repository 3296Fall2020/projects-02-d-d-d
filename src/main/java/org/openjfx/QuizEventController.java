package org.openjfx;

import dnd.combat.Combat;
import dnd.events.QuizEvent;
import dnd.events.RandomEventGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizEventController extends App implements Initializable {

    //the current combat
    public Combat combat;

    //variables for the fxml file
    @FXML
    public Text eventText; //the main text for the event screen

    //a String with the current round's description
    public String eventDescription = "";

    //the event
    public QuizEvent event;

    //an event generator
    private RandomEventGenerator eventGenerator;

    @FXML
    Text characterStats;

    //buttons on the screen
    @FXML
    Button choiceAButton;
    @FXML
    Button choiceBButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventGenerator = new RandomEventGenerator(player);
        this.event = eventGenerator.generateQuizEvent();
        this.eventDescription = event.getEventDescription();
        showStats();
        showDescription();
    }

    //Show player stats
    private void showStats(){
        String stats = "Name: " + player.getName() +
                "\nRace & Class: " + player.getRace() + " " + player.getClassMembership() +
                "\nLevel: " + player.getLevel() + " (" + player.getXP() + " XP)" +
                "\nHP: " + player.getHitPoints();
        characterStats.setText(stats);
    }

    private void clearDescription(){
        event.setEventDescription("");
        this.eventDescription = "";
    }

    // Updates the event description Text field with the completed event description String.
    private void showDescription(){
        eventText.setText(this.eventDescription);
    }

    // Builds
    private void addToDescription(String text){
        eventDescription += text + "\n";
    }

    @FXML
    public void doQuiz() throws IOException {
        App.setRoot("quiz");
    }

    @FXML
    public void refuseQuiz() throws IOException {
        App.setRoot("game");
    }

}
