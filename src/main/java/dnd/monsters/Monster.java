package dnd.monsters;

import dnd.weapons.Weapon;
import dnd.dice.Dice;

public abstract class Monster {

    public Dice dice;

    public String name;
    public String type;
    public String desc;
    public int hp;
    public int initiative;
    public Weapon weapon;
    public int damageDie;

    public int strMod;
    public int dexMod;
    public int conMod;
    public int intlMod;
    public int wisMod;
    public int chaMod;

    /** Getter methods for monster stats. **/
    public Monster(){
        this.dice = new Dice();
    }
    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }
    public int getInitiative(){
        return this.initiative;
    }
    public int getHP(){
        return this.hp;
    }
    public Weapon getWeapon(){
        return this.weapon;
    }

    /** Getter methods for modifiers.
        Monsters only appear in combat; we only need to know their modifiers. **/
    public int getStrMod(){
        return this.strMod;
    }
    public int getDexMod(){
        return this.dexMod;
    }
    public int getConMod(){
        return this.conMod;
    }
    public int getIntlMod(){
        return this.intlMod;
    }
    public int getWisMod(){
        return this.wisMod;
    }
    public int getChaMod(){
        return this.chaMod;
    }

    /** Gives the monster a weapon and assigns its damage die to the weapon's damage die. **/
    public void getWeapon(Weapon w){
        this.weapon = w;
        this.damageDie = w.getDie();
    }

    public abstract void taunt();

    /** Every monster has a basic attack **/
    public abstract int basicAttack();

    public abstract boolean dodge(int playerRoll);

    public abstract void takeDamage(int dmg);

}
