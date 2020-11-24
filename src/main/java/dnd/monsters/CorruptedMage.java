package dnd.monsters;

import dnd.weapons.*;
import dnd.characters.Character;

public class CorruptedMage extends Monster {

    /** Creates a mage monster of the name "name" with a max HP of "hp" and set its attributes**/
    public CorruptedMage(String name, Character player){
        //mage basic info
        this.name = name;
        this.player = player;
        this.lvl = player.getLevel();
        this.desc = "What once may have been a bright young mage now stands as a hunched, jittery figure. " +
                "Clutched in their blackened hands is a gnarled staff that crackles with dark-gold magic.";
        this.type = "Corrupted Mage";
        this.initHP();
        this.xp = 40;

        //mages get staves
        Staff staff = new Staff();
        this.setWeapon(staff);

        //mage stats
        this.dexMod = 0;
        this.strMod = -2;
        this.conMod = -2;
        this.wisMod = 3;
        this.intMod = 2;
        this.chaMod = 1;

        //set the mage's strings
        this.introString = "The mage sneers at you. \"My name is " + this.name + ",\" they declare. \"Remember " +
                "me, for I will be your downfall.\" You'll see about that!";
        this.tauntString = this.name + " tightens their grip on their staff and hisses at you.";
        this.victoryString = this.name + " has defeated you.";
        this.defeatedString = this.name + " crumples to the ground with an angry shriek. In a final " +
                "burst of energy, they drive their staff into the ground and erupt into a plume of smoke. " +
                "When it clears, nothing more remains except a small scuff on the ground. Congratulations, " +
                "you've defeated " + this.name + "!";
        this.dodgedString = this.name + " dodges the hit.";
        this.isHitString = this.name + " was hit!";
        this.hitsPlayerString = this.name + " lands their attack!";

        this.damageTakenString = "";
        this.damageDealtString = "";
    }


    /** A mage's special ability allows it to regain an amount of HP influenced by its WIS mod.
        Returns 0 because it does 0 damage.
        Can only be used every number of turns represented by specialCD. **/
    public int specialAbility(int mod){
        this.specialCD = 4;
        int regen = 0;

        //determine health to regain
        if (lvl < 2)
            regen += 2 + dice.roll(6) + this.wisMod;
        else if (lvl <= 5)
            regen = 4 + dice.rollSum(6, 2) + this.wisMod;
        else if (lvl <= 8)
            regen = 6 + dice.rollSum(6, 2) + this.wisMod;
        else if (lvl <= 12)
            regen = 8 + dice.rollSum(6, 2) + this.wisMod;
        else if (lvl <= 16)
            regen = 10 + dice.rollSum(6, 3) + this.wisMod;
        else
            regen = 12 + dice.rollSum(6, 3) + this.wisMod;

        //regain health
        this.hp += regen;

        this.damageDealtString = this.name + " suddenly holds their staff to their chest and closes their " +
                "eyes. You hold your breath, expecting them to lash out...but instead a soft blue light washes " +
                "over the mage, healing them for " + regen + " HP!";

        return 0;
    }

}
