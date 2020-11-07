package org.openjfx;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {

    // generates random integer between min and max inclusive
    public int randomIntInRange(int min, int max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }
}
