package dnd.weapons;

import dnd.dice.Dice;

public abstract class Weapon {

    /** Each weapon consists of a name, description, damageDie, and abilityMod.
        The damageDie is the maximum amount of damage a weapon can do.
        The abilityMod is the ability modifier that is added to the weapon's attack roll.
     **/
    public String name;
    public String desc;
    public int damageDie;
    public String ability;

    public String playerUsageString;

    /** Return the name of the weapon. **/
    public String getName(){
        return this.name;
    }

    /** Return the weapon's damage die. **/
    public int getDie(){
        return this.damageDie;
    }

    /** Return which ability the weapon relies on. **/
    public String getAbility(){
        return this.ability;
    }

    /** Return a random String that will describe how the player uses this weapon. **/
    public String getPlayerUsageString(){
        Dice dice = new Dice();
        int rand = dice.roll(4);

        this.playerUsageString = this.getUsage(rand);

        return this.playerUsageString;
    }

    /** An abstract method for randomizing the usage description of the weapon, decided by the int n. **/
    public abstract String getUsage(int n);

}
