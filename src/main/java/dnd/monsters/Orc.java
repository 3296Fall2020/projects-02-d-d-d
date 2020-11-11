package dnd.monsters;

import dnd.weapons.*;
import dnd.characters.Character;

public class Orc extends Monster {

    /** Creates a goblin monster of the name "name" with a max HP of "hp" and set its attributes**/
    public Orc(String name, Character player){
        //orc basic info
        this.name = name;
        this.player = player;
        this.lvl = player.getLevel();
        this.desc = "A hulking orc.";
        this.type = "Orc";
        this.initHP();

        //orcs get clubs
        Club club = new Club();
        this.setWeapon(club);

        //goblin stats
        this.dexMod = -1;
        this.strMod = 3;
        this.conMod = 2;
        this.wisMod = -1;
        this.intMod = -2;
        this.chaMod = -1;

        //set the orc's strings
        this.introString = "The ground trembles slightly as " + this.name + " the " + this.type + " lumbers towards you...";
        this.tauntString = this.name + " roars and beats their chest.";
        this.victoryString = this.name + " has defeated you.";
        this.defeatedString = this.name + " has been defeated.";
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
            dmg = 5 + dice.roll(this.damageDie + mod);
        else if (lvl <= 5)
            dmg = 7 + dice.rollSum(this.damageDie, 2) + mod;
        else if (lvl <= 8)
            dmg = 10 + dice.rollSum(this.damageDie, 3) + mod;
        else if (lvl <= 12)
            dmg = 15 + dice.rollSum(this.damageDie, 4) + mod;
        else if (lvl <= 16)
            dmg = 20 + dice.rollSum(this.damageDie, 5) + mod;
        else
            dmg = 25 + dice.rollSum(this.damageDie, 6) + mod;

        this.damageDealtString = this.name + " gives a guttural shout and points at you, spitting in Orcish, as "+
                "if you, a simple " + player.getRace() + ", has wronged them somehow. Without warning, they " +
                "charge towards you for a special attack, dealing " + dmg + " damage!";

        return dmg;
    }

}
