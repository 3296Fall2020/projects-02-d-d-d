package dnd.combat;

import dnd.dice.Dice;
import dnd.monsters.*;

public class MonsterGenerator {

    private Dice dice;

    public MonsterGenerator(){
        this.dice = new Dice();
    }

    /** Generate a random monster for combat.**/
    public Monster generateRandomMonster(){
        int n = dice.roll(20);

        //pick a monster randomly (modify as necessary)
        if(n <= 10) {
            Spider spider = new Spider("Creed", 50);
            return spider;
        }
        else{
            Goblin goblin = new Goblin("Dwight", 50);
            return goblin;
        }

    }

}
