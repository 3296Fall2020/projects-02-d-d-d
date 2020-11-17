package dnd.quizzes;

import dnd.dice.RandomNumberGenerator;

public class QuizGenerator {
    private MathQuiz quiz;
    private RandomNumberGenerator randomNumGenerator;

    // default quiz is a simple additions quiz
    public QuizGenerator() {
        this.quiz = new AdditionQuiz();
        this.randomNumGenerator = new RandomNumberGenerator();
    }

    // will generate a random quiz based on the user's grade level (1 through 5)
    // result is four integers in an array
    // index 1 and 2 are two integers undergoing operation
    // index 3 is the solution the question
    // index 4 is remainder (if applicable), -1 if not applicable
    // index 5 is the type of operation; 0 = addition, 1 = subtraction, 2 = multiplication, 3 = division, 4 = division with remainder
    public int[] randomQuiz(int gradeLevel) {
        int[] result =  new int[5]; // will contain the result to be returned by the function
        int quizType = 0; // integer to indicate the quiz type; 0 = addition, 1 = subtraction, 2 = multiplication, 3 = addition
        int numDigitsOne = 1; // number of digits in first integer, will be randomly generated
        int numDigitsTwo = 1; // number of digits in second integer, wil be randomly generated
        int quizValues[];
        switch (gradeLevel) { // different cases
            default: case 1: // default case and case 1 are grade level 1
                numDigitsOne = randomNumGenerator.randomIntInRange(1, 2); // number of digits is between 1 and 3
                numDigitsTwo = randomNumGenerator.randomIntInRange(1, 2); // number of digts is between 1 and 3
                quizType = randomNumGenerator.randomIntInRange(0,1); // either addition or subtraction quiz
                break;
            case 2: // grade level 2
                numDigitsOne = randomNumGenerator.randomIntInRange(2, 4); // number of digits is between 2 and 4
                numDigitsTwo = randomNumGenerator.randomIntInRange(2, 4); // number of digts is between 2 and 4
                quizType = randomNumGenerator.randomIntInRange(0,1); // either addition or subtraction quiz
                break;
            case 3:
                numDigitsOne = randomNumGenerator.randomIntInRange(1, 2); // number of digits is between 1 and 2
                numDigitsTwo = randomNumGenerator.randomIntInRange(1, 2); // number of digits is between 1 and 2
                quizType = randomNumGenerator.randomIntInRange(2,3); // either multiplication or division quiz
                break;
            case 4:
                numDigitsOne = randomNumGenerator.randomIntInRange(2, 4); // number of digits is between 2 and 4
                numDigitsTwo = randomNumGenerator.randomIntInRange(2, 4); // number of digits is between 2 and 4
                quizType = randomNumGenerator.randomIntInRange(2,3); // either multiplication or division quiz
                break;
            case 5:
                numDigitsOne = randomNumGenerator.randomIntInRange(3, 5); // number of digits is between 3 and 5
                numDigitsTwo = randomNumGenerator.randomIntInRange(3, 5); // number of digits is between 3 and 5
                quizType = randomNumGenerator.randomIntInRange(2,4); // either multiplication or division or division with remainder quiz
                break;
        }

        switch(quizType){
            case 0: // addition quiz case
                this.quiz = new AdditionQuiz(); // creating new AdditionQuiz
                result[3] = -1; // -1 because remainder is not applicable
                break;
            case 1: // subtraction quiz case
                this.quiz = new SubtractionQuiz(); // creating new Subtraction Quiz
                result[3] = -1; // -1 because remainder is not applicable
                break;
            case 2:
                this.quiz = new MultiplicationQuiz();
                result[3] = -1; // -1 because remainder is not applicable
                break;
            case 3:
                this.quiz = new DivisionQuiz();
                result[3] = -1; // -1 because remainder is not applicable
                break;
            case 4:
                this.quiz = new DivisionWithRemainderQuiz();
                break;
        }
        quizValues = quiz.generateQuiz(numDigitsOne, numDigitsTwo); // generating quiz values
        result[0] = quizValues[0]; // first number undergoing operation
        result[1] = quizValues[1]; // second number undergoing operation
        result[2] = quizValues[2]; // solution
        result[4] = quizType; // quiz type  = 0 for addition quiz

        if (quizType == 4) {// if we have a division with remainder quiz
            result[3] = quizValues[3]; // set remainder value
        }

        return result;
    }
}
