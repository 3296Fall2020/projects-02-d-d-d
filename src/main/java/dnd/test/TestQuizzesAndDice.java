package dnd.test;

import dnd.dice.Dice;
import dnd.quizzes.*;

import java.util.Arrays;

public class TestQuizzesAndDice {

    // tests and demonstrates the generation of math quizzes and dice rolling
    public void testQuizzesAndDice() {
        // Demonstration of dices rolls ------------------------------------------------------
        Dice die = new Dice();
        System.out.println("Rolling a 6-sided die: " + die.roll(6));
        System.out.println("Rolling a 20-sided die: " + die.roll(20));
        System.out.println("Rolling a 10-sided die 3 times and summing all the rolls: " + die.rollSum(10, 3));
        System.out.println("Rolling a 20-sided die 10 times: " + Arrays.toString(die.roll(20, 10)));
        System.out.println("Rolling a 20-sided die with a minimum value of 15: " + die.rollWithMin(20, 15));
        System.out.println("Rolling a 20-sided die with a minimum value of 15 (10 times): " + Arrays.toString(die.rollWithMin(20, 10, 15)));
        System.out.println();
        // -----------------------------------------------------------------------------------

        // Demonstration of quiz generation --------------------------------------------------
        MathQuiz quiz = new AdditionQuiz();
        int[] quizResults = quiz.generateQuiz(3, 5);
        System.out.println(quizResults[0] + " + " + quizResults[1] + " = " + quizResults[2]);

        SubtractionQuiz quiz2 = new SubtractionQuiz();
        int[] quizResults2 = quiz2.generateQuiz(3, 3);
        System.out.println(quizResults2[0] + " - " + quizResults2[1] + " = " + quizResults2[2]);
        int[] quizResults5 = quiz2.generateQuizWithNegatives(4, 4);
        System.out.println(quizResults5[0] + " - " + quizResults5[1] + " = " + quizResults5[2]);

        MathQuiz quiz3 = new MultiplicationQuiz();
        int[] quizResults3 = quiz3.generateQuiz(2, 2);
        System.out.println(quizResults3[0] + " * " + quizResults3[1] + " = " + quizResults3[2]);


        DivisionQuiz quiz4 = new DivisionQuiz();
        int[] quizResults4 = quiz4.generateQuiz(3, 2);
        System.out.println(quizResults4[0] + " / " + quizResults4[1] + " = " + quizResults4[2]);


        DivisionQuiz quiz6 = new DivisionQuiz();
        int[] quizResults6 = quiz6.generateQuizWithRemainder(3, 2);
        System.out.println(quizResults6[0] + " / " + quizResults6[1] + " = " + quizResults6[2] + " R " + quizResults6[3]);
        // -----------------------------------------------------------------------------------

    }
}
