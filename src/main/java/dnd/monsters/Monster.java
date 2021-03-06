package dnd.monsters;

import dnd.characters.Character;
import dnd.weapons.Weapon;
import dnd.dice.Dice;

public abstract class Monster {

    public Dice dice;

    public String name;
    public String type;
    public String desc;
    public int lvl;
    public int hp;
    public int xp;
    public Weapon weapon;
    public int damageDie;

    //The monster knows about the player
    public Character player;

    public int strMod;
    public int dexMod;
    public int conMod;
    public int intMod;
    public int wisMod;
    public int chaMod;

    //cooldowns
    public int specialCD;

    //strings
    public String introString;
    public String tauntString;
    public String victoryString;
    public String defeatedString;
    public String dodgedString;
    public String isHitString;
    public String hitsPlayerString;

    public String damageTakenString;
    public String damageDealtString;

    public Monster(){
        //give the monster a die
        this.dice = new Dice();

        //initialize special cooldown to 0
        this.specialCD = 0;
    }

    /** Monster HP initializer.
     Monster HP scales with the player's level.
     At level 1, its health is equal to a random number between 7-12 HP, plus the monster's CON mod.

     At higher levels, it is calculated by:
     Base + a d12 rolled (PLAYER LEVEL + 2 or 1) times + (MONSTER CON MOD)**/
    public void initHP(){
        if (lvl < 2)
            this.hp = dice.rollWithMin(12, 7) + this.conMod;
        else if (lvl <= 5)
            this.hp = 10 + dice.rollSum(12, lvl+2) + this.conMod;
        else if (lvl <= 10)
            this.hp = 25 + dice.rollSum(12, lvl+2) + this.conMod;
        else if (lvl <= 15)
            this.hp = 35 + dice.rollSum(12, lvl+1) + this.conMod;
        else
            this.hp = 50 + dice.rollSum(12, lvl+1) + this.conMod;
    }

    /** Getter methods for basic monster stats. **/
    public String getName(){
        return this.name;
    }

    public String getType(){
        return this.type;
    }

    public String getDesc(){
        return this.desc;
    }

    public int getHitPoints(){
        return this.hp;
    }

    public int getXP(){
        return this.xp;
    }

    public Weapon getWeapon(){
        return this.weapon;
    }

    //Gets the monster's introduction as a String
    public String getIntroString(){
        return this.introString;
    }

    //Gets the monster's taunt as a String
    public String getTauntString(){
        return this.tauntString;
    }

    //Gets the String that will display when the monster wins combat
    public String getVictoryString(){
        return this.victoryString;
    }

    //Gets the String that will display when the monster loses combat
    public String getDefeatedString(){
        return this.defeatedString;
    }

    //Gets the String that will display when the monster dodges a hit successfully
    public String getDodgedString(){
        return this.dodgedString;
    }

    //Gets the String that will display when the monster has been hit.
    public String getIsHitString(){
        return this.isHitString;
    }

    //Gets the String that will display how much damage the monster takes from the player.
    public String getDamageTakenString(){
        return this.damageTakenString;
    }

    //Gets the String that will display when the monster hits the player.
    public String getHitsPlayerString(){
        return this.hitsPlayerString;
    }

    //Gets the String that will display how much damage the monster deals to the player.
    public String getDamageDealtString(){
        return this.damageDealtString;
    }
    /***/

    /** Setter methods for basic monster stats. **/
    //Overrides the monster's name with newName
    public void setName(String newName){
        this.name = newName;
    }

    //Overrides the monster's current HP with newHP
    public void setHitPoints(int newHP){
        this.hp = newHP;
    }

    //Gives the monster a weapon and assigns its damage die to the weapon's damage die.
    public void setWeapon(Weapon newWeapon){
        this.weapon = newWeapon;
        this.damageDie = newWeapon.getDie();
    }

    //Overrides the monster's XP with xp
    public void setXP(int xp){
        this.xp = xp;
    }
    /****/

    /** Getter methods for the monster's ability modifiers.
        Combat only uses ability modifiers; we only need to set and get the monster's ability modifiers. **/
    public int getStrMod(){
        return this.strMod;
    }
    public int getDexMod(){
        return this.dexMod;
    }
    public int getConMod(){
        return this.conMod;
    }
    public int getIntMod(){
        return this.intMod;
    }
    public int getWisMod(){
        return this.wisMod;
    }
    public int getChaMod(){
        return this.chaMod;
    }

    /** Setter methods for the monster's ability modifiers. **/
    public void setStrMod(int newMod){
        this.strMod = newMod;
    }
    public void setDexMod(int newMod){
        this.dexMod = newMod;
    }
    public void setConMod(int newMod){
        this.conMod = newMod;
    }
    public void setIntMod(int newMod){
        this.intMod = newMod;
    }
    public void setWisMod(int newMod){
        this.wisMod = newMod;
    }
    public void setChaMod(int newMod){
        this.chaMod = newMod;
    }

    /** Combat methods. **/

    /** Monster performs its rolls.
        There is a 30% chance they will try to perform their special ability if it is off cooldown.
        Returns two ints, for the rolls that the monster makes: the attempt to hit, and the damage potentially inflicted. **/
    public int[] doRolls(int mod){
        int rolls[] = new int[2];

        //The monster first tries to attack. The attack roll is 1d20. Store this into the array at index 0.
        int attackRoll = dice.roll(20);
        rolls[0] = attackRoll;

        //This will be the amount of damage the monster will do if it lands the hit.
        int damageRoll;

        //Monster decides how to attack. There is 30% chance of using their special ability (if off cooldown)
        int choice = dice.roll(10);

        if (choice <= 10 && specialCD == 0)
            damageRoll = specialAbility(mod);
        else
            damageRoll = basicAttack(mod);

        //Store the damage roll into the array at index 1.
        rolls[1] = damageRoll;

        return rolls;
    }

    /** Monster tries to dodge.
     Successful if (1d20 + monster's DEX MOD) >= player's attack roll
     **/
    public boolean dodge(int playerRoll){
        int opponentRoll = dice.roll(20) + this.dexMod;

        if (opponentRoll > playerRoll)
            return true;
        else
            return false;
    }

    /** The monster takes dmg amount of damage. **/
    public void takeDamage(int dmg){
        this.damageTakenString = this.name + " takes " + dmg + " damage.";
        this.hp -= dmg;
    }

    /** Updates the monster's special ability cooldown.
        The cooldown reduces by one. If it is already at 0, keep it at 0. **/
    public void reduceCD(){
        if (this.specialCD > 0)
            this.specialCD--;
        else
            this.specialCD = 0;
    }

    /** The monster's basic attack, determined by the damageDie of their weapon.
     Returns the amount of damage the monster does, which scales with the player's level ( = monster's level).

     Takes in an int for the ability modifier to be added to the damage roll. This depends on the monster's
     weapon and is decided during combat, in the Combat class. **/
    public int basicAttack(int mod){
        int dmg;

        if (lvl < 2)
            dmg = dice.roll(this.damageDie + mod);
        else if (lvl <= 5)
            dmg = 3 + dice.rollSum(this.damageDie, 2) + mod;
        else if (lvl <= 8)
            dmg = 5 + dice.rollSum(this.damageDie, 3) + mod;
        else if (lvl <= 12)
            dmg = 5 + dice.rollSum(this.damageDie, 4) + mod;
        else if (lvl <= 16)
            dmg = 7 + dice.rollSum(this.damageDie, 5) + mod;
        else
            dmg = 7 + dice.rollSum(this.damageDie, 6) + mod;

        this.damageDealtString = this.name + " attacks with their " + weapon.name + " for " + dmg + " damage!";
        return dmg;
    }

    /** ABSTRACT METHODS **/
    /** An abstract method for the monster's special ability Has a cooldown represented by the int specialCD.
        Special abilities do more base damage than basic attacks and are unique to each monster.
        Takes in an int for the ability modifier added to the damage roll. **/
    public abstract int specialAbility(int mod);

}
