package dnd.events;

import dnd.characters.Character;

public class LuckEvent extends Event{

    /** Luck events are events that depend purely on the player's luck. **/
    public LuckEvent(Character player) {
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
