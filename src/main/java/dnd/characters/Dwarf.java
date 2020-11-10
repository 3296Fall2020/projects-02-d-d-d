package dnd.characters;

//race based class that inherits character and represents dwarves
public class Dwarf extends Character{

    //Dwarf speak Dwarfish and Common, have a base walking speed of 25 feet, and constitution score increases by 2
    public Dwarf(String name) {
        super(name);
        this.race = "Dwarf";
        this.language = "Dwarfish & Common";
        this.speed = 25;
        this.abilities[2] += 2;
    }
}
