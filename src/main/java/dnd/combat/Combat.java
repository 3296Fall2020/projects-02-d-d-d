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
    public MonsterGenerator myMonsterGenerator;

    //the current opponent
    public Monster opponent;

    //placeholder for weapon usage
    public Weapon playerWeapon;

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
        //set character
        this.player = c;

        //set placeholders
        Sword sword = new Sword();
        this.playerWeapon = sword;

        //create dice
        this.dice = new Dice();

        //start combat at round 0
        this.round = 0;
        this.active = true;
        this.playerVictory = false;

        //create monster generator and generate a monster
        myMonsterGenerator = new MonsterGenerator();
        this.opponent = myMonsterGenerator.generateRandomMonster();

        //check who goes first
        this.playerFirst = decideOrder();

    }

    /** Decides turn order by comparing player and monster dexterity mods.
        The higher dexterity mod goes first (playerFirst = false). **/
    public boolean decideOrder(){
        if (player.getDexterityMod() <= opponent.getDexMod())
            return false;
        return true;
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

    /** COMBAT METHODS **/
    /** Perform opponent turn.**/
    public void opponentTurn(){
        System.out.println("\n**Opponent turn!**");

        int opponentRolls[] = opponent.doRolls();

        int attackRoll = opponentRolls[0];
        int damageRoll = opponentRolls[1];

        //The opponent first tries to attack. If the player fails to dodge, they receive the damage dealt by the opponent.
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

    /** Check if game should go on. **/
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
        If the Monster fails to dodge, the Monster damaged according to the playerWeapon's damage die. **/
    public void attack(){
        System.out.println("You swing the " + playerWeapon.getName() + " towards the " + opponent.getType() + "!");
        int tryAttack = dice.roll(20);
        if(!opponent.dodge(tryAttack)){
            int dmg = dice.roll(playerWeapon.getDie());
            opponent.takeDamage(dmg);
            System.out.println(opponent.getDamageTakenString());
        }
    }

    /** The player tries to dodge the attack. If their d20 roll + DEX modifier exceeds the monster's dice roll, they succeed. **/
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