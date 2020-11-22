package dnd.events;

import dnd.dice.*;
import dnd.characters.Character;
import dnd.events.luckevents.*;
import dnd.events.quizevents.*;
import dnd.events.standardevents.*;

public class RandomEventGenerator {

    // The player
    Character player;

    // Dice
    Dice dice;

    // A random number generator
    RandomNumberGenerator randomizer;

    // ints to keep track of how many of each event types there currently are. must be manually updated :(
    final int numQuizEvents = 2;
    final int numLuckEvents = 2;
    final int numStandardEvents = 2;

    public RandomEventGenerator(Character player){
        this.dice = new Dice();
        this.randomizer = new RandomNumberGenerator();
        this.player = player;
    }

    /** Decide if there should be a combat encounter (40% chance) instead of an event. **/
    public boolean checkForCombat(){
        if (randomizer.randomIntInRange(1,5) <= 2)
                return true;
        return false;
    }

    /** Randomly decide on an event type, retrieve an event of that type, then return the generated event. **/
    public Event generateRandomEvent(){
        int n = dice.roll(4);

        if (n <= 2)
            return getStandardEvent();
        else if (n == 3)
            return getQuizEvent();
        else
            return getLuckEvent();
    }

    /** Event getter methods. **/

    /** FOR NOW, INDIVIDUAL EVENT CLASSES MUST BE HARD-CODED AND MANUALLY FACTORED INTO ITS DESIGNATED SETTER BELOW. **/

    public Event getStandardEvent(){
        int n = randomizer.randomIntInRange(1, numStandardEvents);

        if (n == 1)
            return (new StandardEvent1(player));
        else
            return (new StandardEvent2(player));

    }

    public Event getQuizEvent(){
        int n = randomizer.randomIntInRange(1, numQuizEvents);

        if (n == 1)
            return (new QuizEvent1(player));
        else
            return (new QuizEvent2(player));

    }

    public Event getLuckEvent(){
        int n = randomizer.randomIntInRange(1, numLuckEvents);

        if (n == 1)
            return (new LuckEvent1(player));
        else
            return (new LuckEvent2(player));

    }

}