package dnd.characters;


import dnd.weapons.*;

//Race based elf class
public class Elf extends Character{

    //Elves speak Common and Elvish, have a base walking speed of 30 feet, and dexterity score increases by 2
    //increase hit point by 2
    public Elf(String name) {
        super(name);
        this.race = "Elf";
        this.language = "Elvish and Common";
        this.speed = 30;
        this.abilities[1] += 2;
        this.alignment = "Chaotic Good";
        this.hitPoints += 2;
    }

    //Override setWeapon method such that if the weapon is something good for dwarfs,
    // then their ability scores increase
    // Elves show proficiency in swords and bows So theyhave +1 intelligence
    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        if (weapon.equals(new Sword()) || weapon.equals(new Bow())) {
            int i = getIntelligence();
            i += 1;
            setIntelligence(i);
        }
    }


}
