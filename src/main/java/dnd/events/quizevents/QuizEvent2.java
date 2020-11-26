package dnd.events.quizevents;

import dnd.characters.Character;
import dnd.events.QuizEvent;
import dnd.events.StandardEvent;

public class QuizEvent2 extends QuizEvent {

    /** Quiz events incorporate math quizzes that must be answered correctly to succeed. **/
    public QuizEvent2(Character player) {
        super(player);

        //build the event:
        this.setNPCName(this.getRandomName());
        this.buildDescription();
    }

    public void buildDescription(){
        String desc = "This is quiz event 2 with NPC " + getNPCName();

        this.setEventDescription(desc);
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
