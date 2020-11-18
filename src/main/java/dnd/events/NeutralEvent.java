package dnd.events;

import dnd.characters.Character;

public class NeutralEvent extends Event {

    /** Neutral events are events that simply occur, and may or may not have an effect on the player. **/
    public NeutralEvent(Character player) {
        super(player);
    }

    @Override
    public String chooseA() {
        String ret = "Chose A";

        return ret;
    }

    @Override
    public String chooseB() {
        String ret = "Chose B";

        return ret;
    }

    @Override
    public String chooseC() {
        String ret = "Chose C";

        return ret;
    }

    @Override
    public String passEvent() {
        String ret = "Passed";

        return ret;
    }

    @Override
    public String failEvent() {
        String ret = "Failed";

        return ret;
    }
}
