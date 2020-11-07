package org.openjfx;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private RandomNumberGenerator randomNumberGenerator;

    public Dice() {
        this. randomNumberGenerator = new RandomNumberGenerator();
    }

    // rolls a single die with possible values between 1 and dieSize
    public int roll(int dieSize) {
        return randomNumberGenerator.randomIntInRange(1, dieSize);
    }

    // rolls multiple dice (number of dice given by numRolls)
    // size of the dice is given by dieSize
    public int[] roll(int dieSize, int numRolls) {
        int result[] = new int[numRolls];
        for (int i = 0; i < numRolls; i++) {
            result[i] = randomNumberGenerator.randomIntInRange(1, dieSize);
        }
        return result;
    }

    // rolls multiple dice of various sizes
    // the sizes of the dice are given as an array, which is an input
    // e.g. an input of [6, 10, 20] would roll 3 dice - one 6-sided die, one 10-sized die, and one 20-sided die
    public int[] roll(int[] diceSizes) {
        int numRolls = diceSizes.length;
        int result[] = new int[numRolls];
        for (int i = 0; i < numRolls; i++) {
            result[i] = randomNumberGenerator.randomIntInRange(1, diceSizes[i]);
        }
        return result;
    }

    // rolls a die with sides between minRoll and dieSize
    // e.g. an input of (6,2), would roll a die with sides 2, 3, 4, 5, 6
    public int rollWithMin(int dieSize, int minRoll) {
        return randomNumberGenerator.randomIntInRange(minRoll, dieSize);
    }

    // rolls multiple dice with sides between minRoll and dieSize
    // e.g. an input of (6, 3, 2) would rolle 3 dice with sides 2, 3, 4, 5, 6
    public int[] rollWithMin(int dieSize, int numRolls, int minRoll) {
        int result[] = new int[numRolls];
        for (int i = 0; i < numRolls; i++) {
            result[i] = randomNumberGenerator.randomIntInRange(minRoll, dieSize);
        }
        return result;
    }

    // rolls multiple dice each with minimum and maximum values
    // max sizes are given as an array in diceSizes
    // min sizes are given as an array in minRolls
    public int[] rollWithMin(int[] diceSizes, int[] minRolls) {
        int numRolls = diceSizes.length;
        int result[] = new int[numRolls];
        for (int i = 0; i < numRolls; i++) {
            result[i] = randomNumberGenerator.randomIntInRange(minRolls[i], diceSizes[i]);
        }
        return result;
    }


}
