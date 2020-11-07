package org.openjfx;

public class AdditionQuiz extends MathQuiz{
    private RandomNumberGenerator randomNumberGenerator;

    public AdditionQuiz() {
        super();
    }

    // will return an array of three integers, where index 0 and 1 are the two numbers to be added
    // and index 2 is the correct answer (i.e. the sum of indices 0 and 1);
    // the parameters control the difficulty of the math quiz
    // numDigitsOne is the number of digits desired for the first summand
    // numDigitsTwo is the number of digits desired for the second summand
    @Override
    public int[] generateQuiz(int numDigitsOne, int numDigitsTwo) {
        int[] quizNumbers = this.generateQuizNumbers(numDigitsOne, numDigitsTwo);
        int solution = quizNumbers[0] + quizNumbers[1];
        int quizResult[] = {quizNumbers[0], quizNumbers[1], solution};
        return quizResult;
    }
}
