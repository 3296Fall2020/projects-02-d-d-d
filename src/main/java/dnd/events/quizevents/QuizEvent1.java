package dnd.events.quizevents;

import dnd.characters.Character;
import dnd.events.QuizEvent;
import dnd.events.StandardEvent;

public class QuizEvent1 extends QuizEvent {

    /** Quiz events incorporate math quizzes that must be answered correctly to succeed. **/
    public QuizEvent1(Character player) {
        super(player);

        //build the event:
        this.setNPCName(this.getRandomName());
        this.buildDescription();
    }

    public void buildDescription(){
        String desc = "This is quiz event 1 with NPC " + getNPCName();

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
