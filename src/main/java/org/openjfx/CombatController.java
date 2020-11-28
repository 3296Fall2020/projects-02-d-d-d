package org.openjfx;

import dnd.combat.Combat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CombatController extends App implements Initializable {

    //a Combat variable for the current instance of combat
    public Combat combat;

    /** These are the various Text "fields" that are used in the Controller's matching .fxml file (CombatController = combat.fxml)
        They are accessed using the .getText() and setText() methods.
        More documentation found at: http://tutorials.jenkov.com/javafx/text.html**/
    @FXML
    public Text description; //the main text for the combat screen
    public Text enemyTurnDescription; //text describing the enemy's turn
    public Text outcomeDescription; //text describing the outcome of the fight
    public Text combatDetails; //contains the player and enemy's HP

    //a String with the current round's description
    public String roundDescription = "";

    /** The buttons that are on the combat screen.
        These are instantiated in the Controller so that they can be accessed and modified (such as setting visibility). **/
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

    /** Runs when combat.fxml is first loaded. Displays the combat details, introduces the opponent, and makes
        sure that the "return" button (to return to the main screen) is not visible.
        Initializes the heal countdown to 0 and cdDecreased to false. **/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combat = new Combat(player);
        updateCombatDetails();

        roundDescription = "Uh oh, you've encountered a " + combat.getOpponentType() + "!\n\n";
        roundDescription += combat.opponent.getIntroString() + "\n\n";
        roundDescription += "Combat start!";
        description.setText(roundDescription);

        endCombatButton.setVisible(false);
        this.healCD = 0;
    }

    /** Print the updated combat details each round.
        The method combat.getCombatDetails() handles putting the player/enemy healths in Strings in the Combat class;
        This function simply fetches that String and sets it to the combatDetails Text field in combat.fxml. **/
    private void updateCombatDetails(){
        combatDetails.setText(combat.getCombatDetails() + "\n\n");
    }

    /** Print the description of the round on the screen. Replaces previous round description. **/
    private void showRoundDescription(){
        description.setText(this.roundDescription);
    }

    /** Append to the round description. **/
    private void updateRoundDescription(String text){
        roundDescription += text + "\n";
    }

    /** clears the description Text field. **/
    @FXML
    private void clearText(){
        description.setText("");
    }

    /** Examining the opponent. Updates the roundDescription with the opponent's description. **/
    @FXML
    private void examine() throws IOException {
        String examineText = "\n" + combat.examineOpponent();
        updateRoundDescription(examineText);
        showRoundDescription();
    }

    /** Attacking the opponent. Depending on who goes first, either the player attacks and then the opponent
        follows up with their own attack, or the opponent attacks first and then the player attacks.

        Combines the output strings of each attack using updateRoundDescription(), then shows the combined
        description with showRoundDescription().

        Lastly, updates the player's heal cooldown with updateHealCD() and checks on the status of
        combat with updateCombat().**/
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
        updateHealCD();
        updateCombat();
    }

    /** The player heals.
        If heal is off cooldown, allow it to happen (following the turn order).
        Otherwise, display how many more turns the player needs to wait and forces them to
        make another choice.**/
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

    /** If heal is on cooldown (healCD > 0), decrement it.
        If at 0, keep it at 0. **/
    private void updateHealCD() {
        if (this.healCD > 0) {
            this.healCD--;
        }
        else
            this.healCD = 0;
    }

    /** The opponent's turn. Adds their turn description to the roundDescription.**/
    @FXML
    private void opponentTurn() throws IOException{
        updateRoundDescription("It's " + combat.opponent.getName() + "'s turn!");
        String opponentTurnText = combat.opponentTurn();
        updateRoundDescription(opponentTurnText);
    }

    /** Flee combat. Does not reward XP. **/
    @FXML
    private void flee() throws IOException {
        updateRoundDescription("You fled combat!");
        App.setRoot("game");
    }

    /** Check on the combat status. If someone has won (someone's HP dropped to 0), show the end screen.
        Otherwise, update the combat details. **/
    @FXML
    private void updateCombat() throws IOException {
        if (combat.checkForWin()) {
            showEndScreen();
        }
        else{
            updateCombatDetails();
        }
    }

    /** Shows the endscreen. The endscreen should update the description Text field with the combat's outcomeString,
        make all other combat buttons invisible so the player can no longer access them,
        then make the endCombatButton visible so the player can return to the previous page.**/
    @FXML
    private void showEndScreen() throws IOException {
        description.setText(combat.getOutcomeString());

        examineButton.setVisible(false);
        attackButton.setVisible(false);
        healButton.setVisible(false);
        fleeButton.setVisible(false);
        endCombatButton.setVisible(true);
    }

    /** End combat **/
    @FXML
    private void endCombat() throws IOException {
        App.setRoot("game");
    }

}
