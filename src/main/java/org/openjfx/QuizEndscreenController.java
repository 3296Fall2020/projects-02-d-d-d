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

public class QuizEndscreenController extends App implements Initializable {

    //variables for the fxml file
    @FXML
    public Text eventText; //the main text for the event screen

    //a String with the current round's description
    public String description = "";

    //the event
    public QuizEvent event;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.description = setDescription();
        this.description += giveRewards();

        eventText.setText(this.description);
    }

    // Updates the event description Text field with the completed event description String.
    private String setDescription(){
        String ret = "\n\n";

        ret += "Quiz success! ";

        return ret;
    }

    // Gives the quiz success reward and returns a String describing the reward.
    public String giveRewards(){
        String ret = "\n\n";

        ret += "You earned a reward! ";

        return ret;
    }

    // Return to the game
    @FXML
    public void exitQuiz() throws IOException {
        App.setRoot("game");
    }

}
