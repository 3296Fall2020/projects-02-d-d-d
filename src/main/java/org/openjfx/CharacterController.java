package org.openjfx;
//This contains the GUI setup for the character customization page
import dnd.characters.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import dnd.characters.*;
import javafx.scene.text.Text;

import java.io.IOException;

public class CharacterController {

    private String charText;
    private Character current;

    @FXML
    Button continueButton; // button to continue once the user has finished customizing

    @FXML
    TextField nameField; // field for the user to their character name

    @FXML
    ComboBox<String> raceCombo; // field for user to choose their race

    @FXML
    Button initCharacter; //button to init character

    @FXML
    Button viewStats; // text box that will show stats once character is created

    @FXML
    Text statsDisplay; // shows character stats to user

    // sets the primary.fxml page to be the page in focus
    @FXML
    private void setPrimary() throws IOException {
        App.setRoot("primary");
    }

    //creates a character and sets the character name as name and race as what the user chose
    @FXML
    private void initCharacter() throws IOException {
        CharacterFactory c = new CharacterFactory();
        current = c.createCharacter(raceCombo.getSelectionModel().getSelectedItem(), nameField.getText());
    }

    //shows stats of character if button is pressed
    @FXML
    public void seeStats(ActionEvent actionEvent) {
        charText = "Name: " + current.getName() + "\nRace: " + current.getRace()
                + "\nAlignment: " + current.getAlignment() + "\nClass: " +
                current.getClassMembership() + "\nLanguage : " + current.getLanguage() +
                "\n\nStrength: " + current.getStrength() +
                "\tDexterity: " + current.getDexterity() +
                "\nConstitution: " + current.getConstitution() +
                "\tIntelligence: " + current.getIntelligence() +
                "\nWisdom: " + current.getWisdom() +
                "\tCharisma: " + current.getCharisma() +
                "\n\nSpeed : " + current.getSpeed() + " feet " +
                "\nXP: " + current.getXP() + " points " +
                "\nLevel " + current.getLevel() +
                "\nWeapon: " + current.getWeapon().getName();
        statsDisplay.setText(charText);
    }
}
