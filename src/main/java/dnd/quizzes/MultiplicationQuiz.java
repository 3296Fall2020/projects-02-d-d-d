package dnd.quizzes;

import dnd.dice.RandomNumberGenerator;

public class MultiplicationQuiz extends MathQuiz {
    private RandomNumberGenerator randomNumberGenerator;

    public MultiplicationQuiz() {
        super();
    }

    // will return an array of three integers, where index 0 and 1 are the two numbers to be multiplied
    // and index 2 is the correct answer (i.e. the product of indices 0 and 1);
    // the parameters control the difficulty of the math quiz
    // numDigitsOne is the number of digits desired for the first multiplicand
    // numDigitsTwo is the number of digits desired for the second multiplicand
    @Override
    public int[] generateQuiz(int numDigitsOne, int numDigitsTwo) {
        int[] quizNumbers = this.generateQuizNumbers(numDigitsOne, numDigitsTwo);
        int solution = quizNumbers[0] * quizNumbers[1];
        int quizResult[] = {quizNumbers[0], quizNumbers[1], solution};
        return quizResult;
    }
}
