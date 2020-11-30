package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dnd.characters.Character;
import dnd.characters.CharacterFactory;
import dnd.characters.UserNameSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MainMenuController extends App implements Initializable {

    public Text titleField;
    public Text characterDescriptionField;

    private String gameName = UserNameSingleton.getInstance().getUserName().getText();

    public Button newGameButton;
    public Button loadGameButton;
    public Button logOutButton;

    //Creates a new game. The player must create a new character.
    @FXML
    private void newGame() throws IOException {
        //make new game here & do character creation
        CharacterFactory c = new CharacterFactory();
        //be default, the character first created will always be a human with the same name as the user
        player = c.createCharacter("Human", UserNameSingleton.getInstance().getUserName().getText());

        App.setRoot("character");
    }

    //Sets the current player character to the saved character
    @FXML
    public void loadGame(ActionEvent actionEvent) throws IOException {
        player = getSavedCharacter();
        App.setRoot("game");
    }

    //Gets a saved character. Currently, the game only supports one character per game.
    public Character getSavedCharacter(){
        return characterList.get(0);
    }

    //If current game does not have an existing characterList, create an empty characterList; otherwise, return
    //the player's saved characterList
    public void initCharacterList(){
        if (sessionManager.fetchCharactersForGame(gameName) == null){
            characterList = new ArrayList<Character>();
        }
        else{
            characterList = sessionManager.fetchCharactersForGame(gameName);
        }
    }

    // Display the character details from the existing save
    public void showSaveDetails(){
        String info = "An existing save was found: \n";

        // Only gets the most recently saved character by default
        Character c = getSavedCharacter();

        info += "\nName: " + c.getName();
        info += "\nLevel: " + c.getLevel() + " (" + c.getXP() + " XP)";
        info += "\nRace & class: " + c.getRace() + " " + c.getClassMembership();
        info += "\nHP: " + c.getHitPoints();

        characterDescriptionField.setText(info);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize the character list from the user's save
        initCharacterList();

        //If the player has no saved characters, disable the load function.
        //Otherwise, show the saved character and allow the player to load the game.
        if (characterList.isEmpty()) {
            loadGameButton.setVisible(false);
            characterDescriptionField.setText("No saves found! Start a new game?");
        }
        else {
            showSaveDetails();
        }
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        App.setRoot("login");
    }
}
