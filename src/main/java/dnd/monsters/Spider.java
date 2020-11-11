package dnd.monsters;

import dnd.weapons.*;

public class Spider extends Monster {

    /** Creates a spider monster of the name "name" with a max HP of "hp" and set its attributes **/
    public Spider(String name, int hp){
        //spider basic info
        this.name = name;
        this.hp = hp;
        this.desc = "A large, probably poisonous spider.";
        this.type = "Spider";

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

    public int basicAttack(){
        int dmg = dice.roll(weapon.getDie());
        this.damageDealtString = this.name + " dealt " + dmg + " damage!";
        return dmg;
    }

    public int specialAbility(){
        int dmg = 0;
        this.damageDealtString = this.name + " performs their special ability for " + dmg + " damage!";
        return 0;
    }

}