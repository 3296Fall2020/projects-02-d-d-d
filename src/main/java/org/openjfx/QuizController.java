package org.openjfx;


import dnd.quizzes.QuizGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizController extends App implements Initializable{

    private String quizText;
    private int solution;
    static int gradeLevel = 1;
    int remainderSolution;
    QuizGenerator generator = new QuizGenerator();


    @FXML
    Button generateQuiz; // Generates new quiz
    @FXML
    Button Submit; // Submits the user's solution
    @FXML
    Text quizPrompt; // prompts the user with a quiz
    @FXML
    TextField solutionField; // field for the user to enter the solution
    @FXML
    TextField remainderField; // field for the user to enter the remainder (in cases where the division quiz has a remainder)
    @FXML
    Label remainderLabel; // Label for the remainder field
    @FXML
    TextField gradeLevelField; // field to enter the user's grade level
    @FXML
    Button submitGradeLevel; // button to submit the user's grade level
    @FXML
    Button continueButton; // button to continue once the user has solved the quiz
    @FXML
    Text correctnessIndicator; // text box that will indicate if the user's solution is correct

    // runs when quiz.fxml is first loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            generateQuizText(); // making a new quiz
        } catch (IOException e) {
            e.printStackTrace();
        }
        correctnessIndicator.setVisible(false); // making correctness indicator invisible
        continueButton.setStyle("-fx-background-color: #26ff72; -fx-border-color: #01752a; -fx-border-width: 1;"); // making button green
        continueButton.setVisible(false); // making continue button invisible
        if(remainderSolution == -1) { // case with no remainder (i.e. no division with remainder quiz)
            remainderField.setVisible(false); // making remainder field invisible
            remainderLabel.setVisible(false); // making remainder label invisible
        }
        else { // case with remainder (i.e. we have a division with remainder quiz)
            remainderField.setVisible(true); // making remainder field visible
            remainderLabel.setVisible(true); // making remainder label visible
        }

    }

    // creating the text that contains the math quiz prompt for the user to solve
    @FXML
    private void generateQuizText() throws IOException{
        int[] quizResults = generator.randomQuiz(gradeLevel);
        this.solution = quizResults[2]; // storing the solution to the quiz
        this.remainderSolution = quizResults[3]; // storing the remainder solution (will be -1 if not applicable)
        if (quizResults[4] == 0) { // addition quiz case
            this.quizText = (quizResults[0] + " + " + quizResults[1] + " = ?");
        }
        else if (quizResults[4] == 1) { // subtraction quiz case
            this.quizText = (quizResults[0] + " - " + quizResults[1] + " = ?");
        }
        else if (quizResults[4] == 2) { // multiplication quiz case
            this.quizText = (quizResults[0] + " × " + quizResults[1] + " = ?");
        }
        else if (quizResults[4] == 3) { // division quiz case
            this.quizText = (quizResults[0] + " ÷ " + quizResults[1] + " = ?");
        }
        else if (quizResults[4] == 4) { // division with remainder quiz case
            this.quizText = (quizResults[0] + " ÷ " + quizResults[1] + " = ?");
        }

        if(quizResults[4] == 4) { // if we have a division with remainder quiz
            remainderLabel.setVisible(true); // set remainder label to be visible
            remainderField.setVisible(true); // set remainder field to be visible
        }
        else {
            remainderLabel.setVisible(false); // set remainder label to be invisible
            remainderField.setVisible(false); // set remainder field to be invisible
        }

        quizPrompt.setText(quizText); // setting the quiz text to be the newly generated quiz
    }

    // sets the game.fxml page to be the page in focus
    @FXML
    private void returnToGame() throws IOException {
        App.setRoot("game");
    }

    // Checks the user's entered solution for correctness
    // The page will indicate if the user is correct and allow the user to continue if they are correct
    @FXML
    private void checkSolution() {
        try {
            int userSolution = Integer.parseInt(solutionField.getText()); // getting user input
            if(remainderSolution == -1) { // no remainder as part of solution (i.e. no division with remainder quiz)
                if (userSolution == solution) { // if the user's input is the correct solution
                    correctnessIndicator.setVisible(true); // set correctness indicator to be visible
                    continueButton.setVisible(true); // set continue button to be visible
                    correctnessIndicator.setFill(Color.GREEN); // setting the text color to be green
                    correctnessIndicator.setText("Correct!! You may now continue by clicking the button at the bottom!");
                }
                else {
                    correctnessIndicator.setVisible(true); // set correctness indicator to be visible
                    correctnessIndicator.setFill(Color.RED); // setting the text color to be green
                    correctnessIndicator.setText("Sorry, but that is incorrect. Please try again.");
                }
            }
            else { // there is a remainder (i.e. we have a division with remainder quiz)
                int userRemainderSolution = Integer.parseInt(remainderField.getText()); // getting user's remainder input
                if (userSolution == solution && userRemainderSolution == remainderSolution) { // if solution and remainder are correct
                    correctnessIndicator.setVisible(true); // set correctness indicator to be visible
                    continueButton.setVisible(true); // set continue button to be visible
                    correctnessIndicator.setFill(Color.GREEN); // set indicator text to be green
                    correctnessIndicator.setText("Correct!! You may now continue by clicking the button below!");
                }
                else{ // solution or remainder is incorrect
                    correctnessIndicator.setVisible(true); // set correctness indicator to be visible
                    correctnessIndicator.setFill(Color.RED); // set indicator text to be red
                    correctnessIndicator.setText("Sorry, but that is incorrect. Please try again.");
                }
            }
        }
        catch(final NumberFormatException e) {
            // do nothing
        }

    }

    // changes the user's grade level (and therefore the difficulty of the quizzes)
    @FXML
    private void submitGradeLevel() {
        try {
            int userGradeLevel = Integer.parseInt(gradeLevelField.getText()); // get user input
            this.gradeLevel = userGradeLevel; // set user grade level
        }
        catch(final NumberFormatException e) {
            // do nothing
        }

        try {
            generateQuizText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
