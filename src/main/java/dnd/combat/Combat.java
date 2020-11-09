package dnd.combat;

import dnd.monsters.*;
import dnd.weapons.*;
import dnd.characters.Character;
import dnd.dice.Dice;

public class Combat {

    /* CHARACTER VARIABLES */

    //Ints representing the player's stats. These are placeholders and will be replaced by references to the Character class.
    public Character player;

    //A MonsterGenerator for generating a random monster
    private MonsterGenerator myMonsterGenerator;

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

        //create monster
        this.opponent = myMonsterGenerator.generateRandomMonster();

        //check who goes first, based on dexterity scores
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
    public void newRound(Monster opponent){
        this.round++;
        System.out.println("\n\nRound " + this.round + " start!\n");
    }

    /** RUN COMBAT UNTIL IT ENDS. **/
    public void runCombat(){
        Monster opponent = this.opponent;

        //iterates through rounds while combat is active
        while(active)
        {
            newRound(opponent);
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
        opponent.taunt();

        //The opponent attacks, which the player attempts to dodge.
        if(!playerDodge()) {
            int newHP;
            newHP = player.getHitPoints() - opponent.basicAttack();
            player.setHitPoints(newHP);
        }

        checkForWin();
    }

    /** Perform player turn. **/
    public void playerTurn(){
        System.out.println("Player health: " + player.getHitPoints());
        System.out.println("Enemy health: " + opponent.getHitPoints());
        System.out.println("Current weapon: " + playerWeapon.getName());
        System.out.println("Pick an action: \n1. Attack \n2. Use item \n3. Flee");

        int choice;
        /*
        Scanner kb = new Scanner(System.in);
        choice = Integer.parseInt(kb.nextLine());

        if (choice == 1)
            attack();
        else if (choice == 2)
            openInventory();
        else
            flee();*/

        //ATTACKS BY DEFAULT. Implement choice with GUI here!!!
        attack();
        checkForWin();
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

    /** The player attacks the Monster, who is damaged according to the playerWeapon's damage die. **/
    public void attack(){
        System.out.println("You swing the sword towards the " + opponent.type + "!");
        int tryAttack = dice.roll(20);
        if(!opponent.dodge(tryAttack)){
            int dmg = dice.roll(playerWeapon.getDie());
            opponent.takeDamage(dmg);
        }
    }

    /** The player tries to dodge the attack. If their d20 roll + DEX modifier exceeds the monster's dice roll, they succeed. **/
    public boolean playerDodge(){
        int tryDodge = dice.roll(20) + player.getDexterityMod();
        int opponentAttack = dice.roll(20);
        if(tryDodge >= opponentAttack){
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

        String ret = "Your opponent falls to the ground, defeated.\nCongratulations, you won this fight!";
        active = false;
        return ret;
    }

    /** Lose combat, then end it. **/
    public String loseCombat(){
        //perform loss consequences

        String ret = "You fall to the ground, defeated.\nYou lost this fight!";
        active = false;

        return ret;
    }

    /** Flee combat, then end it. **/
    public void flee(){
        System.out.println("You fled combat!");
        active = false;
    }

}