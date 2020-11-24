package dnd.events;

import dnd.dice.*;

public class RandomEventGenerator {

    // Dice
    Dice dice;

    // A random number generator
    RandomNumberGenerator randomizer;

    public RandomEventGenerator(){
        this.dice = new Dice();
        this.randomizer = new RandomNumberGenerator();
    }

}