package org.openjfx;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import dnd.integration.*;

import java.io.IOException;

public class LoginController extends App {

    private SessionManager sessionManager;

    //buttons on the login screen
    @FXML
    Button loginButton;
    @FXML
    Button createProfile;
    @FXML
    TextField username;
    @FXML
    TextField password;


    //if user can be created, it is created. otherwise, user is prompted to choose another username and password
    //if the user is created, they will be taken to the primary controller screen.
    @FXML
    private void createProfile() throws IOException {

//        if (sessionManager.createUser(username.getText(), password.getText())) {
            App.setRoot("primary");
//        } else {
//            username.setText("Sorry! Username or password already taken.");
//            password.setText("Sorry! Username or password already taken.");
//        }

    }

    //Login and authenticate session based on username and password given. If the given username and password are not
    //in the system, then the user will be prompted to try again.
    // If it is correct, the primary controller will be shown.
    @FXML
    private void loginButton() throws IOException {
      //  if (sessionManager.authenticateSession(username.getText(), password.getText())) {
            App.setRoot("primary");
//        } else {
//            username.setText("Username or password not correct");
//            password.setText("Username or password not correct");
//        }

    }


}
