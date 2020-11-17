package dnd.events;

import dnd.dice.Dice;
import dnd.dice.RandomNumberGenerator;
import dnd.characters.Character;

public class NeutralEventBuilder extends EventBuilder{

    public NeutralEventBuilder(Character player){
        super(player);
    }

    public NeutralEvent buildEvent(){
        NeutralEvent event;
        numChoices = randomizeNumChoices();

        if(numChoices == 2) {
            event = buildEvent2Choices();
            event.numChoices = 2;
        }
        else{
            event = buildEvent3Choices();
            event.numChoices = 3;
        }

        return event;
    }

    @Override
    public NeutralEvent buildEvent2Choices(){
        NeutralEvent event = new NeutralEvent(player);

        return event;
    }

    @Override
    public NeutralEvent buildEvent3Choices(){
        NeutralEvent event = new NeutralEvent(player);

        return event;
    }

}
