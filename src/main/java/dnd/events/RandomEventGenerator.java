package dnd.events;

import dnd.dice.*;
import dnd.characters.Character;
import dnd.events.standardevents.*;

public class RandomEventGenerator {

    // The player
    private Character player;

    // Dice
    private Dice dice;

    // A random number generator
    private RandomNumberGenerator randomizer;

    // ints to keep track of how many of each event types there currently are. must be manually updated :(
    private final int numStandardEvents = 3;

    public RandomEventGenerator(Character player){
        this.dice = new Dice();
        this.randomizer = new RandomNumberGenerator();
        this.player = player;
    }

    /** Decide what type of event to do.
     Combat (25% chance) = return 1
     Quiz (20% chance) = return 2
     Standard event (50% chance) = return 0**/
    public int decideEventType(){
        if (dice.roll(20) <= 5)
            return 1;
        else if (dice.roll(10) <= 4)
            return 2;
        else
            return 0;
    }

    /** Randomly decide on an event type, retrieve an event of that type, then return the generated event. **/
    public StandardEvent generateRandomStandardEvent(){
        int n = randomizer.randomIntInRange(1, numStandardEvents);

        if (n == 1)
            return (new StandardEvent1(player));
        else if (n == 2)
            return (new StandardEvent2(player));
        else
            return (new StandardEvent3(player));
    }

    /** Randomly decide on an event type, retrieve an event of that type, then return the generated event. **/
    public QuizEvent generateQuizEvent() {
        return (new QuizEvent(player));
    }

}