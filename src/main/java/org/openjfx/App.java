package org.openjfx;

import dnd.characters.Character;
import dnd.integration.SessionManager;
import dnd.test.TestQuizGeneration;
import dnd.test.TestQuizzesAndDice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Character player;
    public static SessionManager sessionManager;
    public static List<Character> characterList;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.setTitle("Dungeons, Dragons, & Digits");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {


        // Demonstrating test quizzes and dice rolls ------------------
        TestQuizzesAndDice test = new TestQuizzesAndDice(); // creating test object
        test.testQuizzesAndDice(); // calling test function
        // -------------------------------------------------------------

        // Testing random quiz generation -----------------------------
        TestQuizGeneration testQuizGeneration = new TestQuizGeneration();
        testQuizGeneration.testQuizGeneration();
        // ------------------------------------------------------------

        launch();
    }

}