package org.openjfx;
//This contains the GUI setup for the character customization page
import dnd.characters.Character;
import dnd.integration.SessionManager;
import dnd.weapons.WeaponFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import dnd.characters.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CharacterController extends App implements Initializable {

    //By default, a human character with same name as user is created upon start of new game.
    private String charText;

    private String gameName = UserNameSingleton.getInstance().getUserName().getText();

    @FXML
    Button continueButton; // button to continue once the user has finished customizing

    @FXML
    TextField nameField; // field for the user to their character name

    @FXML
    ComboBox<String> raceCombo; // field for user to choose their race


    @FXML
    Text statsDisplay; // shows character stats to user

    @FXML
    ComboBox<String> weaponCombo; // field for user to choose their weapon

    @FXML
    ComboBox<String> classCombo; // field for user to choose their class

    @FXML
    ComboBox<String> alignmentCombo; // field for user to choose their alignment

    // sets the game.fxml page to be the page in focus
    @FXML
    private void startGame() throws IOException {
        App.setRoot("game");
    }

    //Prints statistics of character
    public void statsSetter() {
        charText = "Name: " + player.getName() + "\nRace: " + player.getRace()
                + "\nAlignment: " + player.getAlignment() + "\nClass: " +
                player.getClassMembership() + "\nLanguage : " + player.getLanguage() +
                "\n\nStrength: " + player.getStrength() +
                "\tDexterity: " + player.getDexterity() +
                "\nConstitution: " + player.getConstitution() +
                "\tIntelligence: " + player.getIntelligence() +
                "\nWisdom: " + player.getWisdom() +
                "\tCharisma: " + player.getCharisma() +
                "\n\nSpeed : " + player.getSpeed() + " feet " +
                "\nXP: " + player.getXP() + " points " +
                "\nLevel: " + player.getLevel() +
                "\nWeapon: " + player.getWeapon().getName();
        statsDisplay.setText(charText);
    }

    //shows stats of character if button is pressed
    @FXML
    public void seeStats(ActionEvent actionEvent) {
        statsSetter();
    }

    //Updates character stats if fields are changed for race, class, weapon, alignment, race, and name
    public void updateCharacter(ActionEvent actionEvent) {
        if (!raceCombo.getSelectionModel().isEmpty()) { //if race is changed
            player = CharacterFactory.createCharacter(raceCombo.getSelectionModel().getSelectedItem(), nameField.getText());
        }
        if (!classCombo.getSelectionModel().isEmpty()) {
            player.setClassMembership((classCombo.getSelectionModel().getSelectedItem())); //set class
        }
        if (!weaponCombo.getSelectionModel().isEmpty()) {
            player.setWeapon(WeaponFactory.createWeapon(weaponCombo.getSelectionModel().getSelectedItem())); //set weapon
        }
        if (!alignmentCombo.getSelectionModel().isEmpty()) {
            player.setAlignment(alignmentCombo.getSelectionModel().getSelectedItem()); //set alignment
        }
        player.setName(nameField.getText());
        statsSetter();
    }

    //creates a character and sets the character name as name and race as what the user chose
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = CharacterFactory.createCharacter("Human", UserNameSingleton.getInstance().getUserName().getText());
        statsSetter();
    }
}
