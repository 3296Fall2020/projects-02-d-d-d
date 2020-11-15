package dnd.monsters;

import dnd.weapons.*;
import dnd.characters.Character;

public class Goblin extends Monster {

    /** Creates a goblin monster of the name "name" with a max HP of "hp" and set its attributes**/
    public Goblin(String name, Character player){
        //goblin basic info
        this.name = name;
        this.player = player;
        this.lvl = player.getLevel();
        this.desc = "A harried-looking goblin, with mottled green-gray skin and beady black eyes. It carries " +
                    "a small club that it occasionally thumps on the ground for no apparent reason other than " +
                    "to entertain itself.";
        this.type = "Goblin";
        this.initHP();

        //goblins get clubs. the goblin's damage die = the club's damage die.
        Club club = new Club();
        this.setWeapon(club);

        //goblin stats
        this.dexMod = 1;
        this.strMod = 2;
        this.conMod = 1;
        this.wisMod = 0;
        this.intMod = -2;
        this.chaMod = -1;

        //set the goblin's strings
        this.introString = this.name + " the Goblin lopes towards you... It looks like they want a fight!";
        this.tauntString = this.name + " sticks out their tongue and cackles at you.";
        this.victoryString = this.name + " has defeated you.";
        this.defeatedString = this.name + " wobbles around with a confused garble, before gravity claims them " +
                "and they crash to the ground. Congratulations, you've defeated " + this.name + " the Goblin!";
        this.dodgedString = this.name + " dodges the hit.";
        this.isHitString = this.name + " was hit!";
        this.hitsPlayerString = this.name + " hit you!";

        this.damageTakenString = "";
        this.damageDealtString = "";
    }


    /** A goblin's special ability that does more base damage than its basic attack.
        Can only be used every number of turns represented by specialCD. **/
    public int specialAbility(int mod){
        this.specialCD = 3;
        int dmg = 0;

        if (lvl < 2)
            dmg = 5 + dice.roll(this.damageDie + mod);
        else if (lvl <= 5)
            dmg = 7 + dice.rollSum(this.damageDie, 2) + mod;
        else if (lvl <= 8)
            dmg = 9 + dice.rollSum(this.damageDie, 3) + mod;
        else if (lvl <= 12)
            dmg = 11 + dice.rollSum(this.damageDie, 4) + mod;
        else if (lvl <= 16)
            dmg = 13 + dice.rollSum(this.damageDie, 5) + mod;
        else
            dmg = 15 + dice.rollSum(this.damageDie, 6) + mod;

        this.damageDealtString = this.name + " drops to a crouch, then suddenly launches themselves into the air " +
                "with a warbling screech! They swing their club over their head in a surprising show of strength, then " +
                "bring it down in a special attack that deals " + dmg + " damage!";

        return dmg;
    }

}
