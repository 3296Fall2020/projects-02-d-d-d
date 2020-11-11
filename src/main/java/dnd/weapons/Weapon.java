package dnd.weapons;

import dnd.dice.Dice;

public abstract class Weapon {

    /** Each weapon consists of a name, description, damageDie, and ability.

        The damageDie is the size of the die rolled for the weapon's damage. This represents
        the maximum damage that the weapon can deal.

        The ability is the ability whose modifier will be added to any attack or damage roll when using this weapon.
        In combat, different weapons rely on different abilities (ex: swords use STR while wands use INT).
     **/
    public String name;
    public String desc;
    public int damageDie;
    public String ability;

    /** A String that describes how the player uses the weapon. Randomized by the getPlayerUsageString() function. **/
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
