package dnd.events;

import dnd.characters.Character;

public class CombatEvent extends Event{

    /** Combat events are random combat encounters with random monsters. **/
    public CombatEvent(Character player) {
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
