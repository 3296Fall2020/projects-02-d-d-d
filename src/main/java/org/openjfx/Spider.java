package org.openjfx;

public class Spider extends Monster {

    /** Creates a spider monster of the name "name" with a max HP of "hp" and its skill modifiers **/
    public void spawn(String name, int hp){
        name = name;
        this.hp = hp;
        this.desc = "A large, probably poisonous spider.";
        this.type = "spider";

        dex = 2;
        str = 0;
        con = 1;
        wis = 1;
        intl = 1;
        cha = -2;

        this.initiative = this.dex;
    }

    /** The spider taunts. **/
    public void taunt(){
        System.out.println("The spider rears back on four of its legs and gnashes its fangs at you!");
    }

    /**
     The spider attacks.
     Note: Value will be randomized via one of the game dice.
     The spider deals a base damage of 5, plus a 1d8
     **/
    public int attack(){
        int dmg = 5;
        dmg += (int)(Math.random() * (8 - 1 + 1) + 1);

        System.out.println("The spider lashes out with one of its claws!");
        System.out.println("Player takes " + dmg + " damage.");

        return dmg;
    }

    /** Spider tries to dodge.
        Successful if (1d20 + spider's DEX) >= player's roll
     **/
    public boolean dodge(int playerRoll){
        int spiderRoll = (int)(Math.random() * (20 - 1 + 1) + 1);

        if ((spiderRoll + this.dex) > playerRoll){
            System.out.println("The spider successfully dodges the hit!");
            return true;
        }
        else{
            System.out.println("The spider takes a hit!");
            return false;
        }
    }

    /** The spider takes damage from weapon. **/
    public void takeDamage(Weapon weapon){
        // weapon deals random damage, up to the maximum of its damage die
        int dmg = (int)(Math.random() * (weapon.getDie() - 1 + 1) + 1);
        System.out.println("Ouch! The spider takes " + dmg + " damage.");
        hp -= dmg;
    }
}