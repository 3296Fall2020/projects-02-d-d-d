package dnd.events;

import dnd.characters.Character;

public class QuizEvent extends Event{

    /** Quiz events incorporate math quizzes that must be answered correctly to succeed. **/
    public QuizEvent(Character player) {
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
