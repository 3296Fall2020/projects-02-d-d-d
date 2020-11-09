package dnd.quizzes;

import dnd.dice.RandomNumberGenerator;

public class SubtractionQuiz extends MathQuiz {

    private RandomNumberGenerator randomNumberGenerator;

    public SubtractionQuiz() {
        super();
    }

    // will return an array of three integers, where index 0 and 1 are the two numbers to be subtracted
    // and index 2 is the correct answer (i.e. index 0 minus index 1);
    // the parameters control the difficulty of the math quiz
    // numDigitsOne is the number of digits desired for the first minuend
    // numDigitsTwo is the number of digits desired for the second subtrahend
    // This default implementation assumes the result will be a positive integer
    // (i.e. the first number (minuend) will be larger than the second number (subtrahend)
    @Override
    public int[] generateQuiz(int numDigitsOne, int numDigitsTwo) {
        int[] quizNumbers = this.generateQuizNumbers(numDigitsOne, numDigitsTwo);
        int numOne = Math.max(quizNumbers[0], quizNumbers[1]); // storing larger number in numOne
        int numTwo = Math.min(quizNumbers[0], quizNumbers[1]); // storing smaller number in numTwo
        int solution = numOne - numTwo;
        int quizResult[] = {numOne, numTwo, solution};
        return quizResult;
    }


    // This implementation will make the result a negative number
    // (i.e. the first number (minuend) will be smaller than the second number (subtrahend)
    public int[] generateQuizWithNegatives(int numDigitsOne, int numDigitsTwo) {
        int[] quizNumbers = this.generateQuizNumbers(numDigitsOne, numDigitsTwo);
        int numOne = Math.min(quizNumbers[0], quizNumbers[1]); // storing larger number in numOne
        int numTwo = Math.max(quizNumbers[0], quizNumbers[1]); // storing smaller number in numTwo
        int solution = numOne - numTwo;
        int quizResult[] = {numOne, numTwo, solution};
        return quizResult;
    }
}
