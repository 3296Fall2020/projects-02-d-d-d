package dnd.combat;

import dnd.monsters.*;
import dnd.weapons.*;
import dnd.characters.Character;
import dnd.dice.Dice;

public class Combat {

    /* CHARACTER VARIABLES */

    //Ints representing the player's stats. These are placeholders and will be replaced by references to the Character class.
    public Character player;

    //placeholder variables
    public int playerHP;
    public int playerDexMod;
    public int playerStrMod;
    public Weapon playerWeapon;

    //dice for combat
    public Dice dice;

    //boolean representing the ongoing status of combat. true if ongoing, false if ended
    public boolean active;

    //boolean representing if the player won or not
    public boolean playerVictory;

    //boolean to signify if the player goes first in combat. true if player goes first, false if opponent goes first.
    public boolean playerFirst;

    //int to signify the current combat round
    private int round;

    //the current opponent
    public Monster opponent;

    public Combat(Character c){
        //set character
        this.player = c;

        //set placeholders
        this.playerHP = 15;
        this.playerDexMod = 3;
        this.playerStrMod = 2;
        Sword sword = new Sword();
        this.playerWeapon = sword;

        //create dice
        this.dice = new Dice();

        initializeCombat();
    }

    /** COMBAT PREP METHODS **/
    /** Initializes combat settings. **/
    public void initializeCombat(){
        //start combat at round 0
        this.round = 0;
        this.active = true;
        this.playerVictory = false;

        //create monster
        this.opponent = getRandomMonster();

        //check who goes first, based on dexterity scores
        this.playerFirst = decideOrder();

    }

    /** Compares player and monster dexterity. Higher dexterity goes first (playerFirst = false). **/
    public boolean decideOrder(){
        if (playerDexMod <= opponent.getDexMod())
            return false;
        return true;
    }

    /** MONSTER SPAWNING METHODS **/
    public Monster getRandomMonster(){
        Monster myMonster;
        int random_num = dice.roll(10);

        if (random_num <= 5)
            myMonster = spawnSpider();
        else
            myMonster = spawnGoblin();

        return myMonster;
    }

    public Monster spawnSpider(){
        Spider spider = new Spider("Creed", 50);
        return spider;
    }

    public Monster spawnGoblin(){
        Goblin goblin = new Goblin("Dwight", 15);
        return goblin;
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
        if(!playerDodge())
            playerHP -= opponent.basicAttack();

        checkForWin();
    }

    /** Perform player turn. **/
    public void playerTurn(){
        System.out.println("Player health: " + playerHP);
        System.out.println("Enemy health: " + opponent.getHP());
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
        if (playerHP <= 0 || opponent.hp <= 0) {
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
        if (playerVictory)
            outcome = "You won!";
        else
            outcome = "You lost!";
        return outcome;
    }

    /** The player attacks the Monster, who is damaged according to the weapon's damage die. **/
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
        int tryDodge = dice.roll(20) + playerDexMod;
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