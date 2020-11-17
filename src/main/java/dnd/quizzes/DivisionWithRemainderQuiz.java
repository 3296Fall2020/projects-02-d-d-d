package dnd.quizzes;

import dnd.dice.RandomNumberGenerator;

public class DivisionWithRemainderQuiz extends MathQuiz{

    private RandomNumberGenerator randomNumberGenerator;

    public DivisionWithRemainderQuiz() {
        super();
    }

    // This implementation will make allow the result to have a remainder
    // (i.e. the second number (divisor) will not evenly divide the first number (dividend)
    @Override
    public int[] generateQuiz(int numDigitsOne, int numDigitsTwo) {
        int[] quizNumbers = this.generateQuizNumbers(numDigitsOne, numDigitsTwo);
        int numOne = Math.max(quizNumbers[0], quizNumbers[1]); // storing larger number in numOne
        int numTwo = Math.min(quizNumbers[0], quizNumbers[1]); // storing smaller number in numTwo
        int solution = numOne / numTwo;
        int remainder = numOne % numTwo;
        int quizResult[] = {numOne, numTwo, solution, remainder};
        return quizResult;
    }
}
