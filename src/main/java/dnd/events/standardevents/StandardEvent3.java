package dnd.events.standardevents;

import dnd.characters.Character;
import dnd.events.StandardEvent;

public class StandardEvent3 extends StandardEvent {

    /** Standard events are events that simply occur, and may or may not have an effect on the player. **/
    public StandardEvent3(Character player) {
        super(player);
    }

    public void buildDescription(){
        String desc = "";

        this.setEventDescription(desc);
    }

    public void buildButtons(){
        this.setButtonAText("Choice A");

        this.setButtonBText("Choice B");

        this.setButtonCText("Choice C");
    }

    public String chooseA() {
        String ret = "";

        return ret;
    }

    public String chooseB() {
        String ret = "";

        return ret;
    }

    public String chooseC() {
        String ret = "";

        return ret;
    }

    public String passEvent() {
        String ret = "";

        return ret;
    }

    public String failEvent() {
        String ret = "";

        return ret;
    }
}
