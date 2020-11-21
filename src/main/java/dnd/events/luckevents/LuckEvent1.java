package dnd.events.luckevents;

import dnd.characters.Character;
import dnd.events.Event;

public class LuckEvent1 extends Event {

    /** Luck events are events that depend purely on the player's luck. **/
    public LuckEvent1(Character player) {
        super(player);

        //build the event:
        this.buildDescription();
        this.buildButtons();
    }

    public void buildDescription(){
        String desc = "This is luck event 1";

        this.setEventDescription(desc);
    }

    public void buildButtons(){
        this.setButtonAText("Choice A");

        this.setButtonBText("Choice B");

        this.setButtonCText("Choice C");
    }

    @Override
    public String chooseA() {
        return "";
    }

    @Override
    public String chooseB() {
        return "";
    }

    @Override
    public String chooseC() {
        return "";
    }

    @Override
    public String passEvent() {
        return "";
    }

    @Override
    public String failEvent() {
        return "";
    }
}
