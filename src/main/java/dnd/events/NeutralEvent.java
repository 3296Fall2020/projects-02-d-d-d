package dnd.events;

import dnd.characters.Character;

public class NeutralEvent extends Event {

    /** Neutral events are events that simply occur, and may or may not have an effect on the player. **/
    public NeutralEvent(Character player) {
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
