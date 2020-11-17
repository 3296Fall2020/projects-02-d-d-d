package dnd.events;

import dnd.dice.Dice;
import dnd.dice.RandomNumberGenerator;
import dnd.characters.Character;

public class NeutralEventBuilder extends EventBuilder{

    public NeutralEventBuilder(Character player){
        super(player);
    }

    public NeutralEvent buildEvent(){
        NeutralEvent event = new NeutralEvent(player);

        event.setEventDescription(randomizeDesc());

        return event;
    }

    public String randomizeDesc(){
        String desc = "This is an event!";

        return desc;
    }
}
