package dnd.quizzes;

import dnd.dice.RandomNumberGenerator;

public class DivisionQuiz extends MathQuiz {

    private RandomNumberGenerator randomNumberGenerator;

    public DivisionQuiz() {
        super();
    }

    // will return an array of three integers, where index 0 and 1 are the two numbers to be divided
    // and index 2 is the correct answer (i.e. index 0 divided by index 1);
    // the parameters control the difficulty of the math quiz
    // numDigitsOne is the number of digits desired for the dividend
    // numDigitsTwo is the number of digits desired for the divisor
    // This default implementation assumes there will be no remainder
    // (i.e. the second number will evenly divide the first)
    @Override
    public int[] generateQuiz(int numDigitsOne, int numDigitsTwo) {
        int[] quizNumbers = this.generateQuizNumbers(numDigitsOne, numDigitsTwo);
        int numOne = Math.max(quizNumbers[0], quizNumbers[1]); // storing larger number in numOne
        int numTwo = Math.min(quizNumbers[0], quizNumbers[1]); // storing smaller number in numTwo
        int solution = numOne / numTwo;
        numOne = numTwo * solution;
        int quizResult[] = {numOne, numTwo, solution};
        return quizResult;
    }

}
