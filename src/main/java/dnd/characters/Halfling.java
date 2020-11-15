package dnd.characters;


import dnd.weapons.*;

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

    //Override setWeapon method such that if the weapon is something good for dwarfs,
    // then their ability scores increase
    // Halflings show proficiency in daggers and wands So they have +1 charisma
    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        if (weapon.equals(new Daggers()) || weapon.equals(new Wand())) {
            int c = getCharisma();
            c += 1;
            setCharisma(c);
        }
    }
}
