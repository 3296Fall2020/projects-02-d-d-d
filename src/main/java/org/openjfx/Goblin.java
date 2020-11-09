package org.openjfx;

public class Goblin extends Monster {

    /** Creates a goblin monster of the name "name" with a max HP of "hp" and its skill modifiers **/
    public void spawn(String name, int hp){
        this.name = name;
        this.hp = hp;
        this.desc = "A harried-looking goblin, with mottled green-gray skin and beady black eyes.";
        this.type = "Goblin";

        this.dex = 1;
        this.str = 2;
        this.con = 1;
        this.wis = 0;
        this.intl = -2;
        this.cha = -1;

        this.initiative = this.dex;
    }

    public void taunt(){
        System.out.println("The goblin cackles and sticks its tongue out at you!");
    }

    /**
     The spider attacks.
     Note: Value will be randomized via one of the game dice.
     The spider deals a base damage of 5, plus a 1d8
     **/
    public int attack(){
        int dmg = 5;
        dmg += (int)(Math.random() * (8 - 1 + 1) + 1);

        System.out.println("The goblin lashes out with one of its claws!");
        System.out.println("Player takes " + dmg + " damage.");

        return dmg;
    }

    /** Goblin tries to dodge.
     Successful if (1d20 + goblin's DEX) >= player's roll
     **/
    public boolean dodge(int playerRoll){
        int gobRoll = (int)(Math.random() * (20 - 1 + 1) + 1);

        if ((gobRoll + this.dex) > playerRoll){
            System.out.println("The goblin successfully dodges the hit!");
            return true;
        }
        else{
            System.out.println("The goblin takes a hit!");
            return false;
        }
    }

    /** The goblin takes damage from weapon. **/
    public void takeDamage(Weapon weapon){
        // weapon deals random damage, up to the maximum of its damage die
        int dmg = (int)(Math.random() * (weapon.getDie() - 1 + 1) + 1);
        System.out.println("Ouch! The goblin takes " + dmg + " damage.");
        hp -= dmg;
    }
}
