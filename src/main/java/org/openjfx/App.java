package org.openjfx;


import dnd.combat.Combat;
import dnd.characters.Character;
import dnd.data.User;
import dnd.quizzes.QuizGenerator;
import dnd.test.TestQuizGeneration;
import dnd.test.TestQuizzesAndDice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Character player;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
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


        //creating a character for testing
        player = new Character("Jim");
        player.setHitPoints(100);

        launch();
    }

}