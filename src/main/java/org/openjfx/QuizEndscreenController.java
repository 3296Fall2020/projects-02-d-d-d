package org.openjfx;

import dnd.combat.Combat;
import dnd.dice.Dice;
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

    private Dice dice = new Dice();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.description = setDescription();
        this.description += giveRewards();

        eventText.setText(this.description);
    }

    // Updates the event description Text field with the completed event description String.
    private String setDescription(){
        String ret = "\n\n";

        ret += "You managed to solve the riddle! ";

        return ret;
    }

    // Gives the quiz success reward and returns a String describing the reward.
    public String giveRewards(){
        String ret = "\n\n";

        ret += "You gained ";
        ret += getRandomReward();

        return ret;
    }

    // Pick a random reward and return a String description of it
    public String getRandomReward(){
        String ret = "";
        int rand = dice.roll(10);
        int gain = dice.rollSum(4, 2);

        if (rand == 1){
            player.setIntelligence(player.getIntelligence() + gain);
            ret = gain + " Intelligence!";
        }
        else if (rand == 2){
            player.setWisdom(player.getWisdom() + gain);
            ret = gain + " Wisdom!";
        }
        else if (rand == 3){
            player.setStrength(player.getWisdom() + gain);
            ret = gain + " Strength!";
        }        else if (rand == 4){
            player.setConstitution(player.getConstitution() + gain);
            ret = gain + " Constitution!";
        }        else if (rand == 5){
            player.setCharisma(player.getCharisma() + gain);
            ret = gain + " Charisma!";
        }
        else if (rand == 6){
            player.setDexterity(player.getDexterity() + gain);
            ret = gain + " Dexterity!";
        }
        else if (rand == 7){
            gain = dice.rollSum(6, 2);
            player.setHitPoints(player.getHitPoints() + gain);
            ret = gain + " HP!";
        }
        else{
            gain = 35;
            player.addXP(gain);
            ret = gain + " XP!";
        }
        return ret;
    }

    // Return to the game
    @FXML
    public void exitQuiz() throws IOException {
        App.setRoot("game");
    }

}
