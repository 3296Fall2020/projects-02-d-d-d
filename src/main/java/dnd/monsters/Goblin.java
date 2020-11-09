package dnd.monsters;

import dnd.weapons.*;

public class Goblin extends Monster {

    /** Creates a goblin monster of the name "name" with a max HP of "hp" and its skill modifiers **/
    public Goblin(String name, int hp){
        //goblin basic info
        this.name = name;
        this.hp = hp;
        this.desc = "A harried-looking goblin, with mottled green-gray skin and beady black eyes.";
        this.type = "Goblin";

        //goblins get clubs
        Club club = new Club();
        this.giveWeapon(club);

        //goblin stats
        this.dexMod = 1;
        this.strMod = 2;
        this.conMod = 1;
        this.wisMod = 0;
        this.intlMod = -2;
        this.chaMod = -1;

        //initiative is equal to the DEX modifier
        this.initiative = this.dexMod;

        //the maximum damage a goblin can do
        this.damageDie = 8;
    }

    /** Monster taunts. **/
    public void taunt(){
        System.out.println("The goblin cackles and sticks its tongue out at you!");
    }

    /**
     The goblin performs its basic attack.
     **/
    public int basicAttack(){
        int dmg = dice.roll(weapon.getDie());

        System.out.println("The goblin lands a blow with its club, dealing " + dmg + " damage!");

        return dmg;
    }

    /** Goblin tries to dodge.
     Successful if (1d20 + goblin's DEX) >= player's roll
     **/
    public boolean dodge(int playerRoll){
        int gobRoll = dice.roll(20) + this.dexMod;

        if (gobRoll > playerRoll){
            System.out.println("The goblin successfully dodges the hit!");
            return true;
        }
        else{
            System.out.println("The goblin takes a hit!");
            return false;
        }
    }

    /** The goblin takes dmg amount of damage. **/
    public void takeDamage(int dmg){
        System.out.println("Ouch! The goblin takes " + dmg + " damage.");
        this.hp -= dmg;
    }
}
