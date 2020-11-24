package dnd.monsters;

import dnd.weapons.*;
import dnd.characters.Character;

public class Dragonling extends Monster {

    /** Creates a dragonling monster of the name "name" with a max HP of "hp" and set its attributes**/
    public Dragonling(String name, Character player){
        //goblin basic info
        this.name = name;
        this.player = player;
        this.lvl = player.getLevel();
        this.desc = "A dragonling that regards you with aloof, intelligent eyes. It appears deceptively " +
                    "small with its wings tucked close to its sides, but even as a fledgling you know that " +
                    "it is already at least three times your size. You wonder what it likes to collect!";
        this.type = "Dragonling";
        this.initHP();
        this.xp = 50;

        //dragonlings get claws
        Claws claws = new Claws();
        this.setWeapon(claws);

        //dragonling stats
        this.dexMod = -1;
        this.strMod = 2;
        this.conMod = 1;
        this.wisMod = 2;
        this.intMod = 2;
        this.chaMod = 1;

        //set the dragonling's strings
        this.introString = "The dragonling known as " + this.name + " stalks towards you, their nostrils " +
                "flared and tail curled high in clear warning of a fight.";
        this.tauntString = this.name + " unfurls their wings and roars!";
        this.victoryString = this.name + " has defeated you.";
        this.defeatedString = this.name + "'s hackles rise as they hiss at you, but to your surprise, they " +
                "spread their wings and abruptly take off. They gradually disappear into the sky, but not " +
                "without leaving you a sense of victory for defeating a dragonling.";
        this.dodgedString = this.name + " dodges the hit.";
        this.isHitString = this.name + " was hit!";
        this.hitsPlayerString = this.name + " lands their attack!";

        this.damageTakenString = "";
        this.damageDealtString = "";
    }


    /** A dragonling's special ability that does more base damage than its basic attack.
     Can only be used every number of turns represented by specialCD.

     This special ability uses the dragonling's CHA mod instead of its standard weapon mod (claws = DEX).**/
    public int specialAbility(int mod){
        this.specialCD = 5;
        int dmg = 0;

        if (lvl < 2)
            dmg = 6 + dice.roll(12) + this.chaMod;
        else if (lvl <= 5)
            dmg = 12 + dice.rollSum(12, 2) + this.chaMod;
        else if (lvl <= 8)
            dmg = 18 + dice.rollSum(12, 2) + this.chaMod;
        else if (lvl <= 12)
            dmg = 24 + dice.rollSum(12, 3) + this.chaMod;
        else if (lvl <= 16)
            dmg = 32 + dice.rollSum(12, 4) + this.chaMod;
        else
            dmg = 38 + dice.rollSum(12, 4) + this.chaMod;

        this.damageDealtString = this.name + " lifts their long neck to the sky and roars, " +
                "before lowering their great, open maw and unleashing a beam of potent magical " +
                "fire towards you, dealing " + dmg + " special damage!";

        return dmg;
    }

}
