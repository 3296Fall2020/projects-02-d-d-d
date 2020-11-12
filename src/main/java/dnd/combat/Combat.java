package dnd.combat;

import dnd.monsters.*;
import dnd.weapons.*;
import dnd.characters.Character;
import dnd.dice.Dice;

public class Combat {

    /* CHARACTER VARIABLES */

    //The player
    public Character player;

    //A MonsterGenerator for generating a random monster
    private MonsterGenerator myMonsterGenerator;

    //the current opponent
    public Monster opponent;

    //keep track of the player's weapon
    public Weapon playerWeapon;

    //int for the monster's weapon's ability modifier (added to monster attack rolls)
    private int opponentWeaponMod;

    //dice for combat
    private Dice dice;

    //boolean representing the ongoing status of combat. true if ongoing, false if ended
    private boolean active;

    //boolean representing if the player won or not
    private boolean playerVictory;

    //boolean to signify if the player goes first in combat. true if player goes first, false if opponent goes first.
    private boolean playerFirst;

    //int to signify the current combat round
    private int round;

    //a String that displays when the player dodges the opponent's attack
    private String playerDodgeString;

    public Combat(Character c){
        //create dice
        this.dice = new Dice();

        //start combat at round 0
        this.round = 0;
        this.active = true;
        this.playerVictory = false;

        //set player
        this.player = c;
        this.playerWeapon = c.getWeapon();

        //create monster generator and generate a monster
        this.myMonsterGenerator = new MonsterGenerator(this.player);
        this.opponent = myMonsterGenerator.generateRandomMonster();
        this.setOpponentWeaponMod();

        //check who goes first
        this.playerFirst = decideOrder();

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
    /** Signifies new round. **/
    public void newRound(){
        this.round++;
        System.out.println("\n\n***Round " + this.round + " start!***");
        System.out.println("Player health: " + player.getHitPoints());
        System.out.println("Enemy health: " + opponent.getHitPoints());
        System.out.println("Current weapon: " + playerWeapon.getName());
    }

    /** RUN COMBAT UNTIL IT ENDS.

     Combat consists of two turns: the player's and opponent's.

     While combat is active:
         -Start new round
         -Check turn order
         -Check if next turn should be allowed (all HPs >= 0)
            -If yes, allow player/opponent turn
            -If no, check who won
         -Check if next turn should be allowed (all HPs >= 0)
            -If yes, allow player/opponent turn
            -If no, check who won
     **/
    public void runCombat(){
        System.out.println(opponent.getIntroString());
        while(active)
        {
            newRound();
            if (playerFirst){
                if(allowTurn())
                    playerTurn();
                if(allowTurn())
                    opponentTurn();
            }
            else{
                if(allowTurn())
                    opponentTurn();
                if(allowTurn())
                    playerTurn();
            }
        }
    }

    /** Decides turn order by comparing player and monster dexterity mods.
     The higher dexterity mod goes first (playerFirst = false). **/
    public boolean decideOrder(){
        if (player.getDexterityMod() <= opponent.getDexMod())
            return false;
        return true;
    }

    /** COMBAT METHODS **/
    /** Perform opponent turn.**/
    public void opponentTurn(){
        System.out.println("\n**Opponent turn!**");

        //opponent randomly taunts
        if(dice.roll(4) == 1)
        System.out.println(opponent.getTauntString());

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
            System.out.println(opponent.getHitsPlayerString());
            System.out.println(opponent.getDamageDealtString());
        }

        //check at the end of the turn if the opponent has won
        checkForWin();
    }

    /** Perform player turn. **/
    public void playerTurn(){
        System.out.println("\n**Player turn!**");

        //allow player to decide what to do
        getPlayerChoice();

        //check at the end of the turn if the player has won
        checkForWin();
    }

    /** Get the player's choice **/
    public int getPlayerChoice(){
        System.out.println("Pick an action: \n 1. Examine opponent\n2. Attack \n3. Use item \n4. Flee");

        // Attacking or fleeing will end the turn.
        // Examining the Monster and opening the inventory does not end the turn.
        boolean endTurn = false;

        // Currently, the player ATTACKS by default.
        // Player choice will be implemented with GUI
        int choice = 2;

        while(!endTurn) {

            if (choice == 1){
                String desc = examineOpponent();
                System.out.println(desc);
            }
            else if (choice == 2) {
                attack();
                endTurn = true;
            }
            else if (choice == 3)
                openInventory();
            else {
                flee();
                endTurn = true;
            }
        }
        return choice;
    }

    /** Check if the turn should be allowed. **/
    public boolean allowTurn(){
        if (!active)
            return false;
        return true;
    }

    /** Checks if player or opponent has won.
        If player or opponent health reaches 0 or lower, return false and switch combat active status to false.
        If the opponent drops to 0 or lower, set playerVictory to true.
        Else, return true. **/
    public boolean checkForWin(){
        if (player.getHitPoints() <= 0 || opponent.hp <= 0) {
            active = false;
            if (opponent.hp <= 0)
                playerVictory = true;
            System.out.println(getOutcome());
            return true;
        }
        return false;
    }

    /** Check who won the battle. **/
    public String getOutcome(){
        String outcome;
        if (playerVictory) {
            outcome = winCombat();
        }
        else {
            outcome = loseCombat();
        }
        return outcome;
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
     **/
    public void attack(){
        System.out.println(playerWeapon.getPlayerUsageString());
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
            System.out.println(opponent.getIsHitString());
            System.out.println(opponent.getDamageTakenString());
        }
        else
            System.out.println(opponent.getDodgedString());
    }

    /** The player tries to dodge the attack.
        The player succeeds if their roll (1d20 + DEX mod) exceeds the opponent's roll (1d20 + weapon ability mod) **/
    public boolean playerDodge(int opponentRoll){
        int dexRoll = dice.roll(20) + player.getDexterityMod();
        if(dexRoll >= opponentRoll){
            System.out.println(playerDodgeString);
            return true;
        }
        return false;
    }

    /** Open inventory. **/
    public void openInventory(){
        // Look through inventory (to be implemented).
    }

    /** Win combat, then end it. **/
    public String winCombat(){
        //give rewards

        String ret = opponent.getDefeatedString();
        active = false;
        return ret;
    }

    /** Lose combat, then end it. **/
    public String loseCombat(){
        //perform loss consequences

        String ret = opponent.getVictoryString();
        active = false;

        return ret;
    }

    /** Flee combat, then end it. **/
    public void flee(){
        System.out.println("You fled combat!");
        active = false;
    }

}