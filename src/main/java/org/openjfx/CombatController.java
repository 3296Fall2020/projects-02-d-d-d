package org.openjfx;

import com.google.common.io.Resources;
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

    public Text description;
    public Text enemyTurnDescription;
    public Text outcomeDescription;

    //a String with the current round's description
    public String roundDescription = "";

    //a String with the current combat's details
    @FXML
    public Text combatDetails;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCombatDetails();
        description.setText(combat.opponent.getIntroString());
        endCombatButton.setVisible(false);
    }

    private void updateCombatDetails(){
        combatDetails.setText(combat.getCombatDetails() + "\n\n");
    }

    private void showRoundDescription(){
        description.setText(this.roundDescription);
    }

    private void updateRoundDescription(String text){
        roundDescription += text + "\n";
    }

    private void showOutcome(String text){
        outcomeDescription.setText(text);
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
    }

    @FXML
    private void heal() throws IOException {
        roundDescription = "";
        if(combat.isPlayerFirst()) {
            updateRoundDescription("It's your turn!");
            String healText = combat.heal();
            updateRoundDescription(healText);
            opponentTurn();
        }
        else{
            opponentTurn();
            updateRoundDescription("It's your turn!");
            String healText = combat.heal();
            updateRoundDescription(healText);
        }
        showRoundDescription();
        updateCombat();
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
