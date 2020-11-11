package dnd.monsters;

import dnd.weapons.*;

public class Spider extends Monster {

    /** Creates a spider monster of the name "name" and set its attributes **/
    public Spider(String name, int playerLvl){
        //spider basic info
        this.name = name;
        this.lvl = playerLvl;
        this.desc = "A large, probably poisonous spider.";
        this.type = "Spider";
        this.initHP();

        //spiders get claws
        Claws claws = new Claws();
        this.setWeapon(claws);

        //spider stats
        this.dexMod = 2;
        this.strMod = 0;
        this.conMod = 1;
        this.wisMod = 1;
        this.intMod = 1;
        this.chaMod = -2;

        //set the spider's strings
        this.introString = this.name + " the " + this.type + " scuttles towards you menacingly... It looks like they want a fight!";
        this.tauntString = this.name + " rears back on four of its legs and taunts you with a hiss.";
        this.victoryString = this.name + " has defeated you.";
        this.defeatedString = this.name + " has been defeated.";
        this.dodgedString = this.name + " dodges the hit.";
        this.isHitString = this.name + " was hit!";
        this.hitsPlayerString = this.name + " hit you!";

        this.damageTakenString = "";
        this.damageDealtString = "";
    }

    public int specialAbility(int mod){
        int dmg = 0;
        this.damageDealtString = this.name + " performs their special ability for " + dmg + " damage!";
        return 0;
    }

}