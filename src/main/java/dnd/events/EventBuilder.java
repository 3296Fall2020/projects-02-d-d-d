package dnd.events;

import dnd.characters.Character;
import dnd.dice.Dice;
import dnd.dice.RandomNumberGenerator;
import javafx.scene.control.Button;

public abstract class EventBuilder {

    // Have a die and RandomNumberGenerator
    Dice dice;
    RandomNumberGenerator randomizer;

    // Access to the character
    Character player;

    // An int for how many choices the event offers (currently supports up to 3)
    int numChoices;

    // Strings for choice button text
    String choiceAButtonText;
    String choiceBButtonText;
    String choiceCButtonText;

    // Strings for each choice's result
    String choiceAResult;
    String choiceBResult;
    String choiceCResult;

    public EventBuilder(Character player){
        this.player = player;
        dice = new Dice();
        randomizer = new RandomNumberGenerator();
    }

    /** A method for choosing whether it should be a 2-choice event or 3-choice event. **/
    public int randomizeNumChoices(){
        if (randomizer.randomIntInRange(2, 3) == 2)
            return 2;
        else
            return 3;
    }

    /** !!! SETTER METHODS !!! **/
    public void setChoiceAButtonText(String text){
        this.choiceAButtonText = text;
    }

    public void setChoiceBButtonText(String text){
        this.choiceBButtonText = text;
    }

    public void setChoiceCButtonText(String text){
        this.choiceCButtonText = text;
    }

    public void setChoiceAResult(String text){
        this.choiceAResult = text;
    }

    public void setChoiceBResult(String text){
        this.choiceBResult = text;
    }

    public void setChoiceCResult(String text){
        this.choiceCResult = text;
    }
    /****/

    /** !!! GETTER METHODS !!! **/
    public String getChoiceAButtonText(){
        return this.choiceAButtonText;
    }

    public String getChoiceBButtonText(){
        return this.choiceAButtonText;
    }

    public String getChoiceCButtonText(){
        return this.choiceAButtonText;
    }

    public String getChoiceAResult(String text){
        return this.choiceAResult;
    }

    public String getChoiceBResult(String text){
        return this.choiceBResult;
    }

    public String getChoiceCResult(String text){
        return this.choiceCResult;
    }
    /****/

    /** ABSTRACT BUILDER METHODS
        These are abstract classes whose implementations are uniquely randomized across different types of Events.
        Each one returns an Event of type that matches the builder's type. **/

    // Build an event with 2 choices.
    public abstract Event buildEvent2Choices();

    // Build an event with 3 choices.
    public abstract Event buildEvent3Choices();
}
