package dnd.events.quizevents;

import dnd.characters.Character;
import dnd.events.Event;

public class QuizEvent2 extends Event {

    /** Quiz events incorporate math quizzes that must be answered correctly to succeed. **/
    public QuizEvent2(Character player) {
        super(player);
    }

    @Override
    public String chooseA() {
        return "";
    }

    @Override
    public String chooseB() {
        return "";
    }

    @Override
    public String chooseC() {
        return "";
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
