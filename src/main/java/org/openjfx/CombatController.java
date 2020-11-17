package org.openjfx;

import com.google.common.io.Resources;
import dnd.combat.Combat;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CombatController extends App implements Initializable {

    //the current combat
    public Combat combat;

    //variables for the fxml file
    @FXML
    public Text description; //the main text for the combat screen
    public Text enemyTurnDescription; //text describing the enemy's turn
    public Text outcomeDescription; //text describing the outcome of the fight
    public Text combatDetails; //contains the player and enemy's HP

    //a String with the current round's description
    public String roundDescription = "";

    //a boolean that keeps track of whether the cooldown has been decreased this round already.
    private boolean cdDecreased;

    //buttons on the combat screen
    @FXML
    Button examineButton;
    @FXML
    Button attackButton;
    @FXML
    Button healButton;
    @FXML
    Button fleeButton;
    @FXML
    Button endCombatButton;

    //an int representing the player's healing ability cooldown (= once per 4 attacks)
    private int healCD;

    //Runs when combat.fxml is first loaded. Displays the combat details, introduces the opponent, and makes
    //sure that the "return" button to return to the main screen is not visible.
    //Initializes the heal countdown to 0 and cdDecreased to false.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combat = new Combat(player);
        updateCombatDetails();
        description.setText(combat.opponent.getIntroString());
        endCombatButton.setVisible(false);
        this.healCD = 0;
        this.cdDecreased = false;
    }

    //print the updated combat details each round
    private void updateCombatDetails(){
        combatDetails.setText(combat.getCombatDetails() + "\n\n");
    }

    private void showRoundDescription(){
        description.setText(this.roundDescription);
    }

    private void updateRoundDescription(String text){
        roundDescription += text + "\n";
    }

    @FXML
    private void clearText(){
        description.setText("");
    }

    @FXML
    private void examine() throws IOException {
        String examineText = combat.examineOpponent();
        updateRoundDescription(examineText);
        showRoundDescription();
    }

    @FXML
    private void attack() throws IOException {
        roundDescription = "";
        if(combat.isPlayerFirst()) {
            updateRoundDescription("It's your turn!");
            String playerTurnText = combat.attack();
            updateRoundDescription(playerTurnText);
            opponentTurn();
        }
        else{
            opponentTurn();
            updateRoundDescription("It's your turn!");
            String playerTurnText = combat.attack();
            updateRoundDescription(playerTurnText);
        }
        showRoundDescription();
        updateCombat();
        updateHealCD();
    }

    @FXML
    private void heal() throws IOException {
        roundDescription = "";

        // If healing is off cooldown, allow it.
        if(this.healCD == 0) {
            if (combat.isPlayerFirst()) {
                updateRoundDescription("It's your turn!");
                String healText = combat.heal();
                updateRoundDescription(healText);
                opponentTurn();
            } else {
                opponentTurn();
                updateRoundDescription("It's your turn!");
                String healText = combat.heal();
                updateRoundDescription(healText);
            }
            this.healCD = 4;
            showRoundDescription();
            updateCombat();
        } // If healing is on cooldown, notify the player and force them to make another choice.
        else{
            updateRoundDescription("Please wait " + this.healCD + " turns before trying to heal again!\n");
            showRoundDescription();

        }
    }

    private void updateHealCD() {
        if (this.healCD > 0) {
            this.healCD--;
        }
        else
            this.healCD = 0;
    }

    @FXML
    private void opponentTurn() throws IOException{
        updateRoundDescription("It's " + combat.opponent.getName() + "'s turn!");
        String opponentTurnText = combat.opponentTurn();
        updateRoundDescription(opponentTurnText);
    }

    @FXML
    private void flee() throws IOException {
        updateRoundDescription("You fled combat!");
        App.setRoot("primary");
    }

    @FXML
    private void updateCombat() throws IOException {
        if (combat.checkForWin()) {
            showEndScreen();
        }
        else{
            updateCombatDetails();
        }
    }

    @FXML
    private void showEndScreen() throws IOException {
        description.setText(combat.getOutcomeString());

        examineButton.setVisible(false);
        attackButton.setVisible(false);
        healButton.setVisible(false);
        fleeButton.setVisible(false);
        endCombatButton.setVisible(true);
    }

    @FXML
    private void endCombat() throws IOException {
        App.setRoot("primary");
    }

}
