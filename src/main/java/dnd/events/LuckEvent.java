package dnd.events;

public class LuckEvent extends Event{

    /** Luck events are events that depend purely on the player's luck. **/
    public LuckEvent(Character player) {
        super(player);
    }

    @Override
    public void passEvent() {

    }

    @Override
    public void failEvent() {

    }
}
