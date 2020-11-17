package dnd.events;

public class CombatEvent extends Event{

    /** Combat events are random combat encounters with random monsters. **/
    public CombatEvent(Character player) {
        super(player);
    }

    @Override
    public void passEvent() {

    }

    @Override
    public void failEvent() {

    }
}
