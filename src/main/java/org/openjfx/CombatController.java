package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import dnd.combat.Combat;
import dnd.characters.Character;

import java.io.IOException;

public class CombatController {

    Combat c;
    Character player;
    int num;

    public Label description;

    @FXML
    private void createCombat() throws IOException {
        player = new Character("Jim");
        c = new Combat(player);
        num = 0;

        App.setRoot("combat.fxml");
    }

    @FXML
    private void attack() throws IOException {
        num++;
        description.setText("You attacked " + num);
    }

}
