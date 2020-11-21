package dnd.events.standardevent;

import dnd.characters.Character;
import dnd.events.Event;

public class StandardEvent2 extends Event {

    /** Neutral events are events that simply occur, and may or may not have an effect on the player. **/
    public StandardEvent2(Character player) {
        super(player);

        //build the event:
        this.buildDescription();
        this.buildButtons();
    }

    public void buildDescription(){
        String desc = "This is standard event 2";

        this.setEventDescription(desc);
    }

    public void buildButtons(){
        this.setButtonAText("Button A!");

        this.setButtonBText("Button B!");

        this.setButtonCText("Button C!");
    }

    public String chooseA() {
        String ret = "Chose A ver 2";

        return ret;
    }

    public String chooseB() {
        String ret = "Chose B ver 2";

        return ret;
    }

    public String chooseC() {
        String ret = "Chose C ver 2";

        return ret;
    }

    public String passEvent() {
        String ret = "Passed";

        return ret;
    }

    public String failEvent() {
        String ret = "Failed";

        return ret;
    }
}
