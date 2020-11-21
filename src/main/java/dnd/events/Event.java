package dnd.events;

import dnd.dice.Dice;
import dnd.dice.RandomNumberGenerator;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import dnd.characters.Character;

public abstract class Event {

    // The player
    public Character player;

    // Have a die and RandomNumberGenerator
    public Dice dice;
    public RandomNumberGenerator randomizer;

    // A string containing the event's main text.
    private String eventDescription;

    // Strings for button text
    String buttonAText;
    String buttonBText;
    String buttonCText;

    //Strings for choice results
    String choiceAResult;
    String choiceBResult;
    String choiceCResult;

    public Event(Character player){
        this.player = player;
        dice = new Dice();
        randomizer = new RandomNumberGenerator();
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
                        "Axel", "Alex", "Sofia", "Max", "Mars", "Chet", "Jun"};
        return words[randomizer.randomIntInRange(0, words.length - 1)];
    }

    //get a random adjective for taste. best if used in the case, "It tasted ___."
    public String getRandomTaste(){
        String[] words = {"earthy", "yummy", "sharp", "sweet", "sour", "heady", "new", "unique",
                        "fruity", "bitter", "tangy", "bold", "bland", "odd", "strange", "good",
                        "delicious", "gross", "spicy", "plain"};
        return words[randomizer.randomIntInRange(0, words.length - 1)];
    }

    //get a random adjective describing the NPC's appearance. best if used in the case, "They looked ___."
    public String getRandomImpression(){
        String[] words = {"nervous", "impatient", "sketchy", "trustworthy", "strong", "weak",
                        "queasy", "pale", "lively", "excitable", ""};
        return words[randomizer.randomIntInRange(0, words.length - 1)];
    }

    //get a random color. best if used in the case, "The ___ object" or "The object was ___."
    public String getRandomColor(){
        String[] words = {"red", "blue", "green", "yellow", "orange", "purple", "magenta", "lavender",
                        "black", "white", "pink", "silver", "brown", "gray", "indigo", "navy", "lime green",
                        "baby blue", "neon pink", "electric blue", "bright yellow", "maroon", "crimson",
                        "scarlet", "lilac", "violet", "fuschia", "marigold", "ochre", "sunflower yellow",
                        "shamrock green", "ash gray", "vivid red", "vivid blue", "vivid green", "vivid purple",
                        "light blue", "light green", "light orange", "light yellow", "soft yellow", "soft pink",
                        "pitch black", "blinding white"};
        return words[randomizer.randomIntInRange(0, words.length - 1)];
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

    /** SETTER & GETTER METHODS FOR CHOICE RESULTS **/

    public void setChoiceAResult(String text){
        this.choiceAResult = text;
    }

    public void setChoiceBResult(String text){
        this.choiceBResult = text;
    }

    public void setChoiceCResult(String text){
        this.choiceCResult = text;
    }

    public String getChoiceAResult(String text){
        return this.choiceAResult;
    }

    public String getChoiceBResult(String text){
        return this.choiceBResult;
    }

    public String getChoiceCResult(String text){
        return this.choiceCResult;
    }

    /** Events have unique choices. Returns String descriptions of the consequences.**/
    public abstract String chooseA();
    public abstract String chooseB();
    public abstract String chooseC();

    /** Events have unique consequences upon pass or failure. Returns String descriptions of the consequences.**/
    public abstract String passEvent();
    public abstract String failEvent();

}