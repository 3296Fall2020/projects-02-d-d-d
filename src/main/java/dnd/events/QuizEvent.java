package dnd.events;

public class QuizEvent extends Event{

    /** Quiz events incorporate math quizzes that must be answered correctly to succeed. **/
    public QuizEvent(Character player) {
        super(player);
    }

    @Override
    public void passEvent() {

    }

    @Override
    public void failEvent() {

    }
}
