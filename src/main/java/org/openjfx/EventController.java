package org.openjfx;

import com.google.common.io.Resources;
import dnd.combat.Combat;
import dnd.events.Event;
import dnd.events.RandomEventGenerator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventController extends App implements Initializable {

    //the current combat
    public Combat combat;

    //variables for the fxml file
    @FXML
    public Text eventText; //the main text for the event screen

    //a String with the current round's description
    public String eventDescription = "";

    //the event
    public Event event;

    //an event generator
    private RandomEventGenerator eventGenerator;

    //buttons on the combat screen
    @FXML
    Button choiceAButton;
    @FXML
    Button choiceBButton;
    @FXML
    Button choiceCButton;

    //Runs when combat.fxml is first loaded. Displays the combat details, introduces the opponent, and makes
    //sure that the "return" button to return to the main screen is not visible.
    //Initializes the heal countdown to 0 and cdDecreased to false.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventGenerator = new RandomEventGenerator(player);
        this.event = eventGenerator.generateRandomEvent();
        prepareButtons();
    }

    // Updates the event description Text field with the completed event description String.
    private void showDescription(){
        eventText.setText(this.eventDescription);
    }

    // Builds
    private void addToDescription(String text){
        eventDescription += text + "\n";
    }

    public void prepareButtons(){
        setButtonText(choiceAButton, event.getButtonAText());
        setButtonText(choiceBButton, event.getButtonBText());
        setButtonText(choiceCButton, event.getButtonCText());
    }

    public Button setButtonText(Button button, String text){
        button.setText(text);
        return button;
    }

    @FXML
    public void choiceAAction(){
        String desc = event.chooseA();
        addToDescription(desc);
        showDescription();
    }

    @FXML
    public void choiceBAction(){
        String desc = event.chooseB();
        addToDescription(desc);
        showDescription();
    }

    @FXML
    public void choiceCAction(){
        String desc = event.chooseC();
        addToDescription(desc);
        showDescription();
    }

}
