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

    //placeholder for weapon usage
    public Weapon playerWeapon;

    //int for the player's weapon's ability modifier (added to player attack rolls)
    private int playerWeaponMod;

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

    public Combat(Character c){
        //create dice
        this.dice = new Dice();

        //start combat at round 0
        this.round = 0;
        this.active = true;
        this.playerVictory = false;

        //set player
        this.player = c;

        //give player a weapon (placeholder until Weapon class integrated into Character class)
        Sword sword = new Sword();
        this.playerWeapon = sword;
        this.setPlayerWeaponMod();

        //create monster generator and generate a monster
        this.myMonsterGenerator = new MonsterGenerator(this.player);
        this.opponent = myMonsterGenerator.generateRandomMonster();
        this.opponentWeaponMod = getOpponentWeaponMod();

        //check who goes first
        this.playerFirst = decideOrder();

        System.out.println("Player lvl: " + player.getLevel() + " || HP: " + player.getHitPoints() + " || Monster HP: " + opponent.getHitPoints());
    }

    /** Check which ability the player weapon relies on and set that ability's modifier as the playerWeaponMod. **/
    public void setPlayerWeaponMod(){
        String ability = playerWeapon.getAbility();

        if (ability == "str")
            this.playerWeaponMod = player.getStrengthMod();
        else if (ability == "dex")
            this.playerWeaponMod = player.getDexterityMod();
        else if (ability == "con")
            this.playerWeaponMod = player.getConstitutionMod();
        else if (ability == "int")
            this.playerWeaponMod = player.getIntelligenceMod();
        else if (ability == "wis")
            this.playerWeaponMod = player.getWisdomMod();
        else
            this.playerWeaponMod = player.getCharismaMod();
    }

    /** Check which ability the monster weapon relies on and return that ability's modifier as an int. **/
    public int getOpponentWeaponMod(){
        Weapon weapon = this.opponent.getWeapon();
        String ability = weapon.getAbility();

        if (ability == "str")
            return opponent.getStrMod();
        else if (ability == "dex")
            return opponent.getDexMod();
        else if (ability == "con")
            return opponent.getConMod();
        else if (ability == "int")
            return opponent.getIntMod();
        else if (ability == "wis")
            return opponent.getWisMod();
        else
            return opponent.getChaMod();
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
        System.out.println(opponent.getTauntString());

        int opponentRolls[] = opponent.doRolls();

        int attackRoll = opponentRolls[0] + opponentWeaponMod;
        int damageRoll = opponentRolls[1];

        //The opponent first tries to attack by rolling their attack roll.
        //The attack roll (1d20 + weapon modifier) must exceed the player's roll (1d20 + DEX mod)
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
        System.out.println("Pick an action: \n1. Attack \n2. Use item \n3. Flee");

        // Currently, the player ATTACKS by default.
        // This should be implemented with GUI
        int choice = 1;

        if (choice == 1)
            attack();
        else if (choice == 2)
            openInventory();
        else
            flee();
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
        int tryAttack = dice.roll(20) + playerWeaponMod;
        if(!opponent.dodge(tryAttack)){
            int dmg = 0;

            if (player.getLevel() < 2)
                dmg = dice.roll(playerWeapon.getDie()) + playerWeaponMod;
            else if (player.getLevel() <= 5)
                dmg = dice.rollSum(playerWeapon.getDie(), 2) + playerWeaponMod;
            else if (player.getLevel() <= 12)
                dmg = dice.rollSum(playerWeapon.getDie(), 3) + playerWeaponMod;
            else if (player.getLevel() <= 18)
                dmg = dice.rollSum(playerWeapon.getDie(), 4) + playerWeaponMod;
            else
                dmg = dice.rollSum(playerWeapon.getDie(), 5) + playerWeaponMod;

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
            System.out.println("You manage to dodge the hit!");
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