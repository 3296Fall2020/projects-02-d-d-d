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

        event.setButtonAText("Choice A");
        if (randomizer.randomIntInRange(1,2) == 1)
            event.setButtonBText("Choice B ver. 1");
        else
            event.setButtonBText("Choice B ver. 2");
        event.setButtonCText("Choice C");

        return event;
    }

    public String randomizeDesc(){
        String desc = "This is an event!";

        return desc;
    }
}
