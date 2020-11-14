package dnd.characters;


//Race based elf class
public class Elf extends Character{

    //Elves speak Common and Elvish, have a base walking speed of 30 feet, and dexterity score increases by 2
    public Elf(String name) {
        super(name);
        this.race = "Elf";
        this.language = "Elvish and Common";
        this.speed = 30;
        this.abilities[1] += 2;
        this.alignment = "Chaotic Good";
    }



}
