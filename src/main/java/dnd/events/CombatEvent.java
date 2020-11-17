package dnd.events;

import dnd.characters.Character;

public class CombatEvent extends Event{

    /** Combat events are random combat encounters with random monsters. **/
    public CombatEvent(Character player) {
        super(player);
    }

    @Override
    public void chooseA() {

    }

    @Override
    public void chooseB() {

    }

    @Override
    public void chooseC() {

    }

    @Override
    public void passEvent() {

    }

    @Override
    public void failEvent() {

    }
}
