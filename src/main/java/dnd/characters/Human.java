package dnd.characters;


//Race based human character class
public class Human extends Character{

    //Humans speak Common, have a base walking speed of 30 feet, and all ability scores increase by one.
    public Human(String name) {
        super(name);
        this.race = "Human";
        this.language = "Common";
        this.speed = 30;
        for (int i = 0; i < abilities.length; i++) {
            this.abilities[i] += 1;
        }
    }
}
