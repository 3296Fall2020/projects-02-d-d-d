package org.openjfx;

import java.util.Random;

public abstract class MathQuiz {
    public abstract int[] generateQuiz(int numDigitsOne, int numDigitsTwo);
    private RandomNumberGenerator randomNumberGenerator;

    public MathQuiz() {
        this.randomNumberGenerator = new RandomNumberGenerator();
    }

    public int[] generateQuizNumbers(int numDigitsOne, int numDigitsTwo) {
        int minOne = (int) Math.pow(10, numDigitsOne - 1); // minimum value for the first summand
        int maxOne = (int) Math.pow(10, (numDigitsOne)) - 1; // maximum value for the second summand

        int minTwo = (int) Math.pow(10, numDigitsTwo - 1); // minimum value for the second summand
        int maxTwo = (int) Math.pow(10, (numDigitsTwo)) - 1; // maximum value for the second summand

        int intOne = randomNumberGenerator.randomIntInRange(minOne, maxOne); // first summand (randomly generated)
        int intTwo = randomNumberGenerator.randomIntInRange(maxOne, maxTwo); // first summand (randomly generated)

        int[] quizNumbers= {intOne, intTwo};
        return quizNumbers;
    }
}
