package dnd.monsters;

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
        this.setWeapon(claws);

        //spider stats
        this.dexMod = 2;
        this.strMod = 0;
        this.conMod = 1;
        this.wisMod = 1;
        this.intlMod = 1;
        this.chaMod = -2;

        //initiative is equal to the DEX modifier
        this.initiative = this.dexMod;

        //the maximum damage a spider can do
        //this.damageDie = claws.getDie();

        //set the spider's strings
        this.tauntString = this.name + " rears back on four of its legs and taunts you with a hiss.";
        this.victoryString = this.name + " has defeated you.";
        this.defeatString = this.name + " has been defeated.";
        this.dodgedString = this.name + " dodges the hit.";
        this.isHitString = this.name + " was hit!";
        this.hitsPlayerString = this.name + " hit you!";

        this.damageTakenString = "";
        this.damageDealtString = "";
    }

    public int basicAttack(){
        int dmg = dice.roll(weapon.getDie());
        this.damageDealtString = this.name + " dealt " + dmg + " damage!";
        return dmg;
    }

}