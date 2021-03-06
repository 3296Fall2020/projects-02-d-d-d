package dnd.events;

import dnd.dice.Dice;
import dnd.dice.RandomNumberGenerator;
import dnd.characters.Character;
import javafx.fxml.FXML;
import org.openjfx.App;

import java.io.IOException;

public class QuizEvent {

    // The player
    public Character player;

    // Have a die and RandomNumberGenerator
    public Dice dice;
    public RandomNumberGenerator randomizer;

    // Keep track of the NPC's name
    public String npcName;

    // A string containing the event's main text.
    private String eventDescription;

    public QuizEvent(Character player){
        this.player = player;
        dice = new Dice();
        randomizer = new RandomNumberGenerator();

        //build the event:
        this.buildDescription();

        //build the event:
        this.setNPCName(this.getRandomName());
        this.buildDescription();
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

    /** WRITING-RELATED METHODS **/
    //add two new lines
    public String newParagraph(){
        return "\n\n";
    }

    //get a random NPC name
    public String getRandomName(){
        String[] words = {"Janus", "Karl", "Magda", "Valerie", "Credence", "Charity", "Mercy", "Lux",
                "Nessie", "Gar", "Eliza", "Safana", "Nell", "Riker", "Amir", "Calen", "Ashe",
                "Roel", "Oliver", "Celine", "Aveline", "Ada", "Dinah", "Bo", "Robb", "Edd", "Ned",
                "Naya", "Wynne", "Zephyr", "Xiaowei", "Sunny", "Giselle", "Robert", "Edward",
                "Axel", "Alex", "Sofia", "Max", "Mars", "Chet", "Jun", "Cassidy", "Jack", "Zachary",
                "Noah", "Mason", "Alfred"};
        return words[randomizer.randomIntInRange(0, words.length - 1)];
    }

    //set the NPC's name
    public void setNPCName(String name){
        this.npcName = name;
    }

    //return the NPC's name
    public String getNPCName(){
        return this.npcName;
    }

    /** Events will have unique functions that dictate the main text of the event and the available choices. **/
    public void buildDescription(){
        String desc = "Out of nowhere, you begin to hear a strange humming... ";

        desc += newParagraph();
        desc += "You blink in confusion, looking around. Where is that coming from? ";

        desc += newParagraph();
        desc += "The air suddenly begins to ripple before you, humming and humming until...\"pop!\" A small pixie " +
                "has appeared!";

        desc += newParagraph();
        desc += "\"Greetings! My name is " + getNPCName() + ",\" giggles the pixie, their wings fluttering about. ";

        desc += newParagraph();
        desc += "Now you understand where that humming was coming from!";

        desc += newParagraph();
        desc += "\"I have a little riddle for you, if you dare accept the challenge! I'll even reward you if you " +
                "get it right.\" " + getNPCName() + " smiles mischievously. \"What do you say?\"";

        this.setEventDescription(desc);
    }

}