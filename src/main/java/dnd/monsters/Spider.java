package dnd.monsters;

import dnd.dice.Dice;
import dnd.weapons.Club;
import dnd.weapons.*;

public class Spider extends Monster {

    /** Creates a spider monster of the name "name" with a max HP of "hp" and its skill modifiers **/
    public Spider(String name, int hp){
        //spider basic info
        this.name = name;
        this.hp = hp;
        this.desc = "A large, probably poisonous spider.";
        this.type = "spider";

        //spiders get claws
        Claws claws = new Claws();
        getWeapon(claws);

        //spider stats
        this.dexMod = 2;
        this.strMod = 0;
        this.conMod = 1;
        this.wisMod = 1;
        this.intlMod = 1;
        this.chaMod = -2;
        this.initiative = this.dexMod;
    }

    /** The spider taunts. **/
    public void taunt(){
        System.out.println("The spider rears back on four of its legs and taunts you with a hiss.");
    }

    /**
     The spider attacks.
     Note: Value will be randomized via one of the game dice.
     The spider deals a base damage of 5, plus a 1d8
     **/
    public int basicAttack(){
        int dmg = dice.roll(weapon.getDie());

        System.out.println("The spider lashes out with its claws, dealing " + dmg + " damage!");

        return dmg;
    }

    /** Spider tries to dodge.
        Successful if (1d20 + spider's DEX) >= player's roll
     **/
    public boolean dodge(int playerRoll){
        int spiderRoll = dice.roll(20) + this.dexMod;

        if (spiderRoll > playerRoll){
            System.out.println("The spider successfully dodges the hit!");
            return true;
        }
        else{
            System.out.println("The spider takes a hit!");
            return false;
        }
    }

    /** The spider takes dmg amount of damage. **/
    public void takeDamage(int dmg){
        System.out.println("Ouch! The spider takes " + dmg + " damage.");
        this.hp -= dmg;
    }
}