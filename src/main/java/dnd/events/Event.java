package dnd.events;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import dnd.characters.Character;

public abstract class Event {

    // The player
    public Character player;

    // A string containing the event's main text.
    private String eventDescription;

    // An int representing how many choices the event involves.
    public int numChoices;

    // Strings for button text
    String buttonAText;
    String buttonBText;
    String buttonCText;

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

    /** SETTER & GETTER METHODS FOR BUTTON TEXT **/
    public void setButtonAText(String text){
        this.buttonAText = text;
    }

    public void setButtonBText(String text){
        this.buttonBText = text;
    }

    public void setButtonCText(String text){
        this.buttonCText = text;
    }

    public String getButtonAText(){
        return this.buttonAText;
    }

    public String getButtonBText(){
        return this.buttonBText;
    }

    public String getButtonCText(){
        return this.buttonCText;
    }

    /** Events have unique choices. Returns String descriptions of the consequences.**/
    public abstract String chooseA();
    public abstract String chooseB();
    public abstract String chooseC();

    /** Events have unique consequences upon pass or failure. Returns String descriptions of the consequences.**/
    public abstract String passEvent();
    public abstract String failEvent();

}