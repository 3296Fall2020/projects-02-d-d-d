package dnd.monsters;

import dnd.weapons.*;
import dnd.characters.Character;

public class Bandit extends Monster {

    /** Creates a bandit monster of the name "name" with a max HP of "hp" and set its attributes**/
    public Bandit(String name, Character player){
        //mage basic info
        this.name = name;
        this.player = player;
        this.lvl = player.getLevel();
        this.desc = "The Bandit stands with all the ease in the world, carrying a slim bow in their hands " +
                "and a quiver full of arrows on their back. With a mask that seems to have become a requirement " +
                "for Bandits (or a fashion statement?), all you know of this one's identity is their alias.";
        this.type = "Bandit";
        this.initHP();

        //bandits get bows
        Bow bow = new Bow();
        this.setWeapon(bow);

        //bandit stats
        this.dexMod = 2;
        this.strMod = -2;
        this.conMod = -1;
        this.wisMod = 0;
        this.intMod = 1;
        this.chaMod = 2;

        //set the bandit's strings
        this.introString = "The Bandit emerges from the dark cloud that seems to somehow follow them " +
                "around, picking at their bow leisurely. You recognize them as " + this.name + "!";
        this.tauntString = this.name + " smirks, twirling an arrow between their fingers before notching it.";
        this.victoryString = this.name + " has defeated you.";
        this.defeatedString = this.name + " collapses to the ground with a cry of agony. However, as you " +
                "approach, they suddenly leap to their feet and shout, \"Got'cha!\" Quicker than you can " +
                "catch them, they somersault away. This looks like a victory for you, though!";
        this.dodgedString = this.name + " dodges the hit.";
        this.isHitString = this.name + " was hit!";
        this.hitsPlayerString = this.name + " lands their attack!";

        this.damageTakenString = "";
        this.damageDealtString = "";
    }


    /** A bandit's special ability that deals more damage than its basic attack.
     Can only be used every number of turns represented by specialCD. **/
    public int specialAbility(int mod){
        this.specialCD = 5;
        int dmg = 0;

        //determine health to regain
        if (lvl < 2)
            dmg = 3 + dice.rollSum(weapon.getDie(), 3) + mod;
        else if (lvl <= 5)
            dmg = 6 + dice.rollSum(weapon.getDie(), 4) + mod;
        else if (lvl <= 8)
            dmg = 9 + dice.rollSum(weapon.getDie(), 5) + mod;
        else if (lvl <= 12)
            dmg = 12 + dice.rollSum(weapon.getDie(), 6) + mod;
        else if (lvl <= 16)
            dmg = 15 + dice.rollSum(weapon.getDie(), 7) + mod;
        else
            dmg = 18 + dice.rollSum(weapon.getDie(), 8) + mod;

        this.damageDealtString = "\"Take that!\" " + this.name + " shouts, but you don't quite move quickly " +
                "enough to stop them. You watch as " + this.name + " leaps into the air, notching and loosing a " +
                "handful of arrows that deal " + dmg + " special damage!";

        return dmg;
    }

}
