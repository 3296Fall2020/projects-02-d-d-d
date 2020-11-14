package dnd.characters;


//Race based halfling character class
public class Halfling extends Character{


    //Halflings speak Halfling and Common, have a base walking speed of 25 feet, and dexterity score increases by 2
    public Halfling(String name) {
        super(name);
        this.race = "Halfling";
        this.language = "Halfling and Common";
        this.speed = 25;
        this.abilities[1] +=2;
        this.alignment = "Lawful Good";
    }
}
