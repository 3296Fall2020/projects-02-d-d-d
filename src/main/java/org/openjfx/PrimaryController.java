package org.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dnd.characters.Character;
import dnd.characters.CharacterFactory;
import dnd.characters.UserNameSingleton;
import dnd.combat.Combat;
import dnd.dice.Dice;
import dnd.dice.RandomNumberGenerator;
import dnd.events.RandomEventGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController extends App{

    @FXML
    private void newGame() throws IOException {
        //make new game here & do character creation
        CharacterFactory c = new CharacterFactory();
        //be default, the character first created will always be a human with the same name as the user
        player = c.createCharacter("Human", UserNameSingleton.getInstance().getUserName().getText());

        App.setRoot("character");
    }

    @FXML
    public void loadGame(ActionEvent actionEvent) throws IOException {
        //set loaded game from profile here
        App.setRoot("game");
    }

}
