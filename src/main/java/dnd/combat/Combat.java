package dnd.combat;

import dnd.monsters.*;
import dnd.weapons.*;
import dnd.characters.Character;
import dnd.dice.Dice;
import javafx.beans.property.StringProperty;

public class Combat {

    //The player
    public Character player;

    //A MonsterGenerator for generating a random monster
    private MonsterGenerator myMonsterGenerator;

    //the current Monster opponent
    public Monster opponent;

    //keep track of the player's weapon
    public Weapon playerWeapon;

    //int for the opponent's weapon's ability modifier (added to opponent attack rolls)
    private int opponentWeaponMod;

    //dice for combat
    private Dice dice;

    //boolean representing if the player won or not
    public boolean playerVictory;

    //String describing the outcome of the battle
    private String outcomeString;

    //boolean to signify if the player goes first in combat. true if player goes first, false if opponent goes first.
    private boolean playerFirst;

    //a String that displays when the player dodges the opponent's attack
    private String playerDodgeString;

    //int for xp earned
    private int xpEarned;

    public Combat(Character c){
        //create dice
        this.dice = new Dice();

        //player hasn't won yet
        this.playerVictory = false;

        //initialize xp earned to 0
        xpEarned = 0;

        //keep track of player and player's weapon.
        this.player = c;
        this.playerWeapon = c.getWeapon();

        //create monster generator and generate a monster
        this.myMonsterGenerator = new MonsterGenerator(this.player);
        this.opponent = myMonsterGenerator.generateRandomMonster();
        this.setOpponentWeaponMod();

        //check who goes first
        this.playerFirst = decideOrder();

        //set the player's dodge string using the opponent's name
        this.playerDodgeString = opponent.name + " tries to attack, but you manage to dodge!";

        //uncomment to see print starting levels and HP
        //System.out.println("Player lvl: " + player.getLevel() + " || HP: " + player.getHitPoints() + " || Monster HP: " + opponent.getHitPoints());
    }

    /** Check which ability the monster weapon relies on and return that ability's modifier as an int. **/
    public void setOpponentWeaponMod(){
        Weapon weapon = this.opponent.getWeapon();
        String ability = weapon.getAbility();

        if (ability == "str")
            this.opponentWeaponMod = opponent.getStrMod();
        else if (ability == "dex")
            this.opponentWeaponMod = opponent.getDexMod();
        else if (ability == "con")
            this.opponentWeaponMod = opponent.getConMod();
        else if (ability == "int")
            this.opponentWeaponMod = opponent.getIntMod();
        else if (ability == "wis")
            this.opponentWeaponMod = opponent.getWisMod();
        else
            this.opponentWeaponMod = opponent.getChaMod();
    }

    /** ROUND HANDLING METHODS **/
    public String getCombatDetails(){
        String ret = "";

        ret += "Your HP: " + player.getHitPoints() + "\n";
        ret += "Your weapon: " + player.getWeapon().getName() + "\n\n";

        ret += "You are fighting " + opponent.getName() + "\n";
        ret += opponent.getName() + "'s HP: " + opponent.getHitPoints() + "\n";

        return ret;
    }

    public String getOpponentType(){
        return opponent.getType();
    }

    public String getOpponentName(){
        return opponent.getName();
    }

    /** Decides turn order by comparing player and monster dexterity mods.
     The higher dexterity mod goes first (playerFirst = false). **/
    public boolean decideOrder(){
        if (player.getDexterityMod() <= opponent.getDexMod())
            return false;
        return true;
    }

    public boolean isPlayerFirst(){
        if (playerFirst == true)
            return true;
        return false;
    }

    /** COMBAT METHODS **/
    /** Perform opponent turn. Returns String describing opponent turn.**/
    public String opponentTurn(){
        String ret = "";

        //opponent randomly taunts
        if(dice.roll(4) == 1)
            ret += opponent.getTauntString() + "\n";

        // Reduce the opponent's cooldown by 1 and perform its attack and damage rolls.
        this.opponent.reduceCD();
        int opponentRolls[] = opponent.doRolls(opponentWeaponMod);

        int attackRoll = opponentRolls[0];
        int damageRoll = opponentRolls[1];

        // The opponent first tries to attack with an attack roll.
        // The attack roll (1d20 + weapon modifier) must exceed the player's counter roll (1d20 + DEX mod).
        // If the player fails to dodge, they receive damage equal to the opponent's damage roll.
        if(!playerDodge(attackRoll)) {
            int newHP;
            newHP = player.getHitPoints() - damageRoll;
            player.setHitPoints(newHP);
            ret += opponent.getHitsPlayerString() + "\n";
            ret += opponent.getDamageDealtString() + "\n";
        }
        else{
            ret += playerDodgeString + "\n";
        }
        return ret;
    }

    /** Get and display a description of the Monster. **/
    public String examineOpponent(){
        return opponent.getDesc();
    }

    /** The player attempts to attack the Monster.
        If the Monster fails to dodge, the Monster damaged according to the playerWeapon's damage die.
        Player damage is calculated by rolling the weapon's damage die then adding the weapon's mod.

        The damage die is rolled once for lvl <2, twice for lvl 2-5, three times for lvl 6-12,
        four times for lvl 13-18, and five times for lvl 19+.

        For example, a lvl 17 character with a Sword (weapon die = 6, weapon mod = STR) will deal:
                                 dmg = 4d6 + player's STR modifier

     Returns a String describing what happened during the player's attack.
     **/
    public String attack(){
        String ret = "";
        ret += playerWeapon.getPlayerUsageString() + "\n";
        int playerWeaponMod = player.getPlayerWeaponMod();
        int tryAttack = dice.roll(20) + playerWeaponMod;
        if(!opponent.dodge(tryAttack)){
            int dmg;

            if (player.getLevel() < 2)
                dmg = 2 + dice.roll(playerWeapon.getDie()) + playerWeaponMod;
            else if (player.getLevel() <= 5)
                dmg = 5 + dice.rollSum(playerWeapon.getDie(), 2) + playerWeaponMod;
            else if (player.getLevel() <= 8)
                dmg = 7 + dice.rollSum(playerWeapon.getDie(), 3) + playerWeaponMod;
            else if (player.getLevel() <= 12)
                dmg = 9 + dice.rollSum(playerWeapon.getDie(), 4) + playerWeaponMod;
            else if (player.getLevel() <= 16)
                dmg = 11 + dice.rollSum(playerWeapon.getDie(), 5) + playerWeaponMod;
            else
                dmg = 13 + dice.rollSum(playerWeapon.getDie(), 6) + playerWeaponMod;

            opponent.takeDamage(dmg);
            ret += opponent.getIsHitString() + "\n";
            ret += opponent.getDamageTakenString() + "\n";
        }
        else {
            ret += opponent.getDodgedString() + "\n";
        }

        return ret;
    }

    /** The player tries to dodge the attack.
        The player succeeds if their roll (1d20 + DEX mod) exceeds the opponent's roll (1d20 + weapon ability mod) **/
    public boolean playerDodge(int opponentRoll){
        int dexRoll = dice.roll(20) + player.getDexterityMod();
        if(dexRoll >= opponentRoll)
            return true;
        return false;
    }

    /** Heal HP. Influenced by the character's healingDie, level, and the higher of their INT or CHA mod.
        Returns a string describing the player's heal action.**/
    public String heal(){
        String healed = "";
        // set the bonus as the CHA or INT mod, depending on which mod is higher
        int bonus;
        if (player.getCharismaMod() > player.getIntelligenceMod())
            bonus = player.getCharismaMod();
        else
            bonus = player.getIntelligenceMod();

        // if both mods are negative, set bonus to 0
        if (bonus < 0)
            bonus = 0;

        // calculate the new HP.
        // to regain HP, player rolls a die the size of their healingDie (1 + LVL) times, then updates the heal String.
        int hp;
        hp = dice.rollSum(player.getHealingDie(), player.getLevel() + 1) + bonus;

        healed = "You close your eyes and reach out to your well of strength, allowing it to spill " +
                "back into your being... You reopen your eyes with a newfound sense of strength. You " +
                "healed " + hp + " HP!\n";

        // add HP healed to current HP.
        hp += player.getHitPoints();

        // set new HP.
        player.setHitPoints(hp);

        return healed;
    }

    /** Return the outcome string. **/
    public String getOutcomeString(){
        return this.outcomeString;
    }

    /** Checks if player or opponent has won.
     If player or opponent health reaches 0 or lower, return false and switch combat active status to false.
     If the opponent drops to 0 or lower, set playerVictory to true.
     Else, return true. **/
    public boolean checkForWin(){
        if (player.getHitPoints() <= 0 || opponent.hp <= 0) {
            if (opponent.hp <= 0) {
                playerVictory = true;
            }
            updateOutcome();
            return true;
        }
        return false;
    }

    /** Check who won the battle. **/
    public void updateOutcome(){
        if (playerVictory) {
            this.outcomeString = winCombat();
        }
        else {
            this.outcomeString = loseCombat();
        }
    }

    /** Win combat, then end it. **/
    public String winCombat(){
        xpEarned = opponent.getXP();
        player.addXP(xpEarned);
        String ret = opponent.getDefeatedString() + "\n\n";
        ret += "You've earned " + xpEarned + " XP.";
        return ret;
    }

    /** Lose combat, then end it. **/
    public String loseCombat(){
        xpEarned = 10;
        player.addXP(xpEarned);
        String ret = opponent.getVictoryString() + "\n\n";
        ret += "You've earned " + xpEarned + " XP.";
        return ret;
    }

    /** Flee combat, then end it. **/
    public String flee(){
        return "You fled combat!";
    }
}