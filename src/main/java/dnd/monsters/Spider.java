package dnd.monsters;

import dnd.weapons.*;
import dnd.characters.Character;

public class Spider extends Monster {

    /** Creates a spider monster of the name "name" and set its attributes **/
    public Spider(String name, Character player){
        //spider basic info
        this.name = name;
        this.player = player;
        this.lvl = player.getLevel();
        this.desc = "A large, probably poisonous spider.";
        this.type = "Spider";
        this.initHP();
        this.xp = 30;

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
        this.defeatedString = "With one last terrible shriek, " + this.name + " pounds its claws into the ground. With startling speed, " +
                "it burrows into the ground and escapes. That shriek sounded like a promise that this wasn't over yet...but for now, " +
                "it looks like you've won!";
        this.dodgedString = this.name + " dodges the hit.";
        this.isHitString = this.name + " was hit!";
        this.hitsPlayerString = this.name + " hit you!";

        this.damageTakenString = "";
        this.damageDealtString = "";
    }

    public int specialAbility(int mod){
        this.specialCD = 4;
        int dmg = 0;

        if (lvl < 2)
            dmg = 2 + dice.rollSum(6, 8);
        else if (lvl <= 5)
            dmg = 3 + dice.rollSum(6, 8) + mod;
        else if (lvl <= 8)
            dmg = 4 + dice.rollSum(6, 8) + mod;
        else if (lvl <= 12)
            dmg = 5 + dice.rollSum(6, 8) + mod;
        else if (lvl <= 16)
            dmg = 6 + dice.rollSum(6, 8) + mod;
        else
            dmg = 7 + dice.rollSum(6, 8) + mod;

        this.damageDealtString = this.name + " coils themselves into a ball and speeds towards you, before " +
                "springing out at the last moment and lashing out with all eight of its legs, dealing "
                + dmg + " special damage!";

        return dmg;
    }

}