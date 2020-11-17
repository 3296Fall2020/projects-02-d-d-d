package dnd.events;

import javafx.scene.text.Text;
import dnd.characters.Character;

public abstract class Event {

    // The player
    public Character player;

    // A string containing the event's main text.
    private String eventDescription;

    // An int representing how many choices the event involves.
    public int numChoices;

    public Event(Character player){
        this.player = player;
    }

    // Set the eventDescription
    public void setEventDescription(String text){
        this.eventDescription = text;
    }

    // Append String text as a new paragraph to the event of the current eventDescription
    public void appendEventDescription(String text){
        this.eventDescription += "\n\n" + text;
    }

    // Return the eventDescription as a String
    public String getEventDescription(){
        return this.eventDescription;
    }

    /** Events have unique choices. **/
    public abstract void chooseA();
    public abstract void chooseB();
    public abstract void chooseC();

    /** Events have unique consequences upon pass or failure. **/
    public abstract void passEvent();
    public abstract void failEvent();

}