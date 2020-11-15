package dnd.characters;

import dnd.weapons.*;

//race based class that inherits character and represents dwarves
public class Dwarf extends Character{

    //Dwarf speak Dwarfish and Common, have a base walking speed of 25 feet, and constitution score increases by 2
    //increase health point by one
    public Dwarf(String name) {
        super(name);
        this.race = "Dwarf";
        this.language = "Dwarfish and Common";
        this.speed = 25;
        this.abilities[2] += 2;
        this.alignment = "Lawful Good";
        this.hitPoints += 1;
    }

    //Override setWeapon method such that if the weapon is something good for dwarfs,
    // then their ability scores increase
    // Dwarfs show proficiency in claws and clubs So claws and clubs have +1 dexterity.
    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        if (weapon.equals(new Claws()) || weapon.equals(new Club())) {
            int d = getDexterity();
            d += 1;
            setDexterity(d);
        }
    }
}
