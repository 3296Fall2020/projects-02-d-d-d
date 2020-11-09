package dnd.weapons;

public abstract class Weapon {

    /** Each weapon consists of a name, description, and damageDie.
        The damageDie is the maximum amount of damage a weapon can do.
     **/
    public String name;
    public String desc;
    public int damageDie;

    /** Return the name of the weapon. **/
    public String getName(){
        return this.name;
    }

    /** Return the weapon's damage die. **/
    public int getDie(){
        return this.damageDie;
    }

}
