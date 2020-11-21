package dnd.events;

import dnd.dice.*;
import dnd.characters.Character;
import dnd.events.luckevents.*;
import dnd.events.quizevents.*;
import dnd.events.standardevent.*;

public class RandomEventGenerator {

    // The player
    Character player;

    // Dice
    Dice dice;

    // A random number generator
    RandomNumberGenerator randomizer;

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

    /** Randomly decide what type of event to make, build an event of that type, then return the built event. **/
    public Event generateRandomEvent(){
        int n = dice.roll(4);

        if (n <= 4)
            return getStandardEvent();
        else if (n == 3)
            return getQuizEvent();
        else
            return getLuckEvent();
    }

    public Event getStandardEvent(){
        int n = randomizer.randomIntInRange(1, 2);

        if (n == 1)
            return (new StandardEvent1(player));
        else
            return (new StandardEvent2(player));

    }

    public Event getQuizEvent(){
        int n = randomizer.randomIntInRange(1, 10);

        if (n == 1)
            return (new QuizEvent1(player));
        else
            return (new QuizEvent2(player));

    }

    public Event getLuckEvent(){
        int n = randomizer.randomIntInRange(1, 10);

        if (n == 1)
            return (new LuckEvent1(player));
        else
            return (new LuckEvent2(player));

    }

}