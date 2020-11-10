package dnd.monsters;

import dnd.weapons.*;

public class Orc extends Monster {

    /** Creates a goblin monster of the name "name" with a max HP of "hp" and set its attributes**/
    public Orc(String name, int hp){
        //orc basic info
        this.name = name;
        this.hp = hp;
        this.desc = "A hulking orc.";
        this.type = "Orc";

        //orcs get clubs
        Club club = new Club();
        this.setWeapon(club);

        //goblin stats
        this.dexMod = -1;
        this.strMod = 3;
        this.conMod = 2;
        this.wisMod = -1;
        this.intlMod = -2;
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