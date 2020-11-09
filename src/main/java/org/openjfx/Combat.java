package org.openjfx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Combat {

    /* CHARACTER VARIABLES */

    //Ints representing the player's stats. These are placeholders and will be replaced by references to the Character class.
    public int playerHP; //hp
    public int playerStr; //strength
    public int playerDex; //dexterity
    public int playerCon; //constitution
    public int playerIntl; //intelligence
    public int playerWis; //wisdom
    public int playerCha; //charisma
    public Weapon playerWeapon;

    //boolean representing the ongoing status of combat. true if ongoing, false if ended
    public boolean active;

    //boolean to signify if the player goes first in combat. true if player goes first, false if opponent goes first.
    public boolean playerFirst;

    //int to signify the current combat round
    private int round;

    //the current opponent
    public Monster opponent;

    /** COMBAT PREP METHODS **/
    /** Initializes combat settings. **/
    public void initializeCombat(){
        //start combat at round 0
        this.round = 0;
        this.active = true;

        //initialize character; placeholder for Character class
        initializeCharacter();

        //create monster
        this.opponent = getRandomMonster();

        //check who goes first, based on dexterity scores
        this.playerFirst = decideOrder();

    }

    /** Initializes placeholder character stats. **/
    public void initializeCharacter(){
        this.playerHP = 100;
        this.playerStr = 13;
        this.playerDex = 15;
        this.playerCon = 11;
        this.playerIntl = 10;
        this.playerWis = 13;
        this.playerCha = 14;

        Sword sword = new Sword();
        this.playerWeapon = sword;
    }

    /** Compares player and monster dexterity. Higher dexterity goes first (playerFirst = false). **/
    public boolean decideOrder(){
        if (playerDex <= opponent.dex){
            System.out.println("You go first!");
            return false;}
        return true;
    }

    /** MONSTER SPAWNING METHODS **/
    public Monster getRandomMonster(){
        Monster myMonster;
        int random_num = (int)(Math.random() * (5 - 1 + 1) + 1);

        if (random_num <= 5)
            myMonster = spawnSpider();
        else
            myMonster = spawnGoblin();

        return myMonster;
    }

    public Monster spawnSpider(){
        Spider spider = new Spider();
        spider.spawn("Creed", 50);
        return spider;
    }

    public Monster spawnGoblin(){
        Goblin goblin = new Goblin();
        goblin.spawn("Dwight", 50);
        return goblin;
    }

    /** ROUND HANDLING METHODS **/
    /** Signifies new round. **/
    public void newRound(Monster opponent){
        this.round++;
        System.out.println("Round " + this.round + " start!\n");
    }

    /** Perform opponent turn.**/
    public void opponentTurn(){
        if (allowNextTurn()){
            opponent.taunt();
            playerHP -= opponent.attack();
        }
        else{
            System.out.println("The " + opponent.type + " looks weak...");
            winCombat();
        }
    }

    /** Perform player turn. **/
    public void playerTurn(){
        System.out.println("Player health: " + playerHP);
        System.out.println("Enemy health: " + opponent.hp);
        //Scanner kb = new Scanner(System.in);

        if (allowNextTurn()){
            System.out.println("Current weapon: " + playerWeapon.getName());
            System.out.println("Pick an action: \n1. Attack \n2. Use item \n3. Flee");

            int choice;
            /*choice = Integer.parseInt(kb.nextLine());

            if (choice == 1)
                attack();
            else if (choice == 2)
                openInventory();
            else
                flee();*/

            attack();
        }
        else{
            System.out.println("Oh no, everything's starting to feel fuzzy...");
            loseCombat();
        }
    }

    /** RUN COMBAT UNTIL IT ENDS. **/
    public void runCombat(){
        Monster opponent = this.opponent;

        //iterates through rounds while combat is active
        while(active)
        {
            newRound(opponent);
            if (playerFirst){
                playerTurn();
                opponentTurn();
            }
            else{
                opponentTurn();
                playerTurn();
            }
        }
    }

    /** COMBAT METHODS **/
    /** Placeholder method for Dice class **/
    public int rolld20(){
        int dmg = (int)(Math.random() * (20 - 1 + 1) + 1);
        return dmg;
    }

    /** Checks if combat should continue running.
     * If player or opponent health reaches 0 or lower, return false and switch combat active status to false.
     * Else, return true.**/
    public boolean allowNextTurn(){
        if (playerHP <= 0 || opponent.hp <= 0) {
            playerHP = 0;
            active = false;
            return false;
        }
        active = true;
        return true;
    }

    /** The player attacks the Monster target, who is damaged according to the weapon's damage die. **/
    public void attack(){
        System.out.println("You swing the sword towards the " + opponent.hp + "!");
        if(opponent.dodge(rolld20())){
            opponent.takeDamage(playerWeapon);
        }
        System.out.println("Monster HP: " + opponent.hp);
    }

    /** Open inventory. **/
    public void openInventory(){
        // Look through inventory (to be implemented).
    }

    /** Win combat, then end it. **/
    public void winCombat(){
        System.out.println("Your opponent falls to the ground, defeated.\nCongratulations, you won this fight!");
        active = false;
    }

    /** Lose combat, then end it. **/
    public void loseCombat(){
        System.out.println("You fall to the ground, defeated.\nYou lost this fight!");
        active = false;
    }

    /** Flee combat, then end it. **/
    public void flee(){
        System.out.println("You fled combat!");
        active = false;
    }

}