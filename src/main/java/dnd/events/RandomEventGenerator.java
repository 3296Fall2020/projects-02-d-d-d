package dnd.events;

import dnd.dice.*;
import dnd.characters.Character;

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
    }

    /** Randomly decide what type of event to make, build an event of that type, then return the built event. **/
    public Event generateRandomEvent(){
        Event event;
        int n = dice.roll(4);

        if (n == 1){
            NeutralEventBuilder neutralEventBuilder = new NeutralEventBuilder(player);
            event = neutralEventBuilder.buildEvent();
        }
        else{
            NeutralEventBuilder neutralEventBuilder = new NeutralEventBuilder(player);
            event = neutralEventBuilder.buildEvent();
        }
        return event;
    }

}