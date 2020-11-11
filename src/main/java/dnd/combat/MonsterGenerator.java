package dnd.combat;

import dnd.characters.Character;
import dnd.dice.*;
import dnd.monsters.*;

public class MonsterGenerator {

    private Dice dice;
    private RandomNumberGenerator randomizer;
    private Character player;
    private int lvl;

    public MonsterGenerator(Character c){
        this.dice = new Dice();
        this.randomizer = new RandomNumberGenerator();
        this.player = c;
        this.lvl = c.getLevel();
    }

    /** Generate a random monster for combat.**/
    public Monster generateRandomMonster(){
        int n = dice.roll(20);
        String name = pickName();

        //pick a type of monster randomly (modify as necessary)
        if(n <= 10)
            return new Spider(name, lvl);
        else
            return new Goblin(name, lvl);
    }

    /** Pick a random name for the monster. **/
    private String pickName(){
        String names[] = new String[]{"Jhdtyjdrthn", "Dddf", "JGSDJDF"};
        int i = randomizer.randomIntInRange(0, names.length - 1);
        return names[i];
    }

}
