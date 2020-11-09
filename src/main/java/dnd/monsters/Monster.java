package dnd.monsters;

import dnd.weapons.Weapon;

public abstract class Monster {

    public String name;
    public String type;
    public String desc;
    public int hp;
    public int initiative;

    public int dex;
    public int str;
    public int con;
    public int wis;
    public int intl;
    public int cha;

    public abstract void spawn(String name, int con);

    public abstract void taunt();

    public abstract int attack();

    public abstract boolean dodge(int playerRoll);

    public abstract void takeDamage(Weapon weapon);

}
