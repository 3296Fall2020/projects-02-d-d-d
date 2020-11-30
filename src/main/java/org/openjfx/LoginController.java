package org.openjfx;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import dnd.integration.*;
import dnd.characters.UserNameSingleton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends App {

    //buttons on the login screen
    @FXML
    Button loginButton;
    @FXML
    Button createProfile;
    @FXML
    TextField username;
    @FXML
    TextField password;

    public Text usernameTaken;

    //if user can be created, it is created. otherwise, user is prompted to choose another username and password
    //if the user is created, they will be taken to the primary controller screen.
    @FXML
    private void createProfile() throws IOException {
        sessionManager = new SessionManager();
        if (sessionManager.createUser(username.getText(), password.getText())) {
            // if you have not logged in at all, client needs to login at least once for cacheing
            UserNameSingleton.getInstance().setUserName(username); //gets username for character creation later using singleton
            sessionManager.ensureAuthentication();
            App.setRoot("mainmenu");
        } else {
            usernameTaken.setFill(Color.RED);
            usernameTaken.setText("Sorry, the username \"" + username.getText() + "\" has already been taken!");
        }

    }

    //Login and authenticate session based on username and password given. If the given username and password are not
    //in the system, then the user will be prompted to try again.
    // If it is correct, the primary controller will be shown.
    @FXML
    private void loginButton() throws IOException {
        sessionManager = new SessionManager();
        if (sessionManager.authenticateSession(username.getText(), password.getText())) {
            UserNameSingleton.getInstance().setUserName(username); //gets username for character creation later using singleton
            App.setRoot("mainmenu");
        } else {
            username.setText("Username or password not correct. Try again!");
        }
    }

}
