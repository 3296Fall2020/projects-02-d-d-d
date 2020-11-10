package dnd.monsters;

import dnd.weapons.*;

public class Goblin extends Monster {

    /** Creates a goblin monster of the name "name" with a max HP of "hp" and set its attributes**/
    public Goblin(String name, int hp){
        //goblin basic info
        this.name = name;
        this.hp = hp;
        this.desc = "A harried-looking goblin, with mottled green-gray skin and beady black eyes.";
        this.type = "Goblin";

        //goblins get clubs
        Club club = new Club();
        this.setWeapon(club);

        //goblin stats
        this.dexMod = 1;
        this.strMod = 2;
        this.conMod = 1;
        this.wisMod = 0;
        this.intlMod = -2;
        this.chaMod = -1;

        //set the goblin's strings
        this.introString = this.name + " the " + this.type + " lopes towards you... It looks like they want a fight!";
        this.tauntString = this.name + " sticks out their tongue and cackles at you.";
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
