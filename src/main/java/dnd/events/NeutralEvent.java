package dnd.events;

public class NeutralEvent extends Event {

    /** Neutral events are events that simply occur, and may or may not have an effect on the player. **/
    public NeutralEvent(Character player) {
        super(player);
    }

    @Override
    public void passEvent() {

    }

    @Override
    public void failEvent() {

    }
}
