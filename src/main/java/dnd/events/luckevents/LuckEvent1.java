package dnd.events.luckevents;

import dnd.characters.Character;
import dnd.events.Event;

public class LuckEvent1 extends Event {

    /** Luck events are events that depend purely on the player's luck. **/
    public LuckEvent1(Character player) {
        super(player);
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
