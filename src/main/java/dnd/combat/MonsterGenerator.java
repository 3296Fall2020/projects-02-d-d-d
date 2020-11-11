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
        int n = dice.roll(6);

        //pick a type of monster randomly (modify as necessary)
        if(n == 1)
            return new Spider(pickSpiderName(), player);
        else if (n == 2)
            return new Orc(pickOrcName(), player);
        else
            return new Goblin(pickGoblinName(), player);
    }

    /** Pick a random spider name. **/
    private String pickSpiderName(){
        String names[] = new String[]{"Ssamara", "Ssamuel", "Sussan", "Anssela", "Rissard", "Sseric",
                                        "Ssoliver", "Sselena", "Ssavid", "Ssilliam", "Rossert", "Ssalmi",
                                        "Ssafiah", "Ssolam", "Ssassah", "Prissilla", "Misster", "Ssora",
                                        "Ssolas", "Ssoey", "Josseph", "Ssunny", "Lissa", "Ssomi", "Missy",
                                        "Sseric", "Ssandrew", "Jasson", "Alessandre"};
        int i = randomizer.randomIntInRange(0, names.length - 1);
        return names[i];
    }

    /** Pick a random goblin name. **/
    private String pickGoblinName(){
        String names[] = new String[]{"Gwil", "Dwigt", "Naala", "Kaht", "Mikl", "Jorg", "Gar", "Jum",
                                        "Bul", "Mas", "Koff", "Hak", "Urgh", "Gur", "Aak", "Orf", "Awch",
                                        "Ffaf", "Prrw", "Jak", "Grr", "Euck", "Chur", "Glug", "Zzit", "Yulk",
                                        "Jol", "Ree", "Bor", "Sik", "Huf", "Pah", "Mmuf"};
        int i = randomizer.randomIntInRange(0, names.length - 1);
        return names[i];
    }

    /** Pick a random orc name. **/
    private String pickOrcName(){
        String names[] = new String[]{"Tiny", "Fluffy", "Grumpy", "Sleepy", "Shorty", "Silly", "Bumpy",
                                        "Smarty", "Speedy", "Happy", "Grumbly", "Chewy", "Crunchy",
                                        "Heavy", "Stinky", "Icky", "Drooly", "Weepy", "Growly", "Gooey",
                                        "Cheesy", "Friendly", "Skippy", "Lazy", "Smacky", "Boomy", "Huffy",
                                        "Giddy", "Jumpy", "Shivery", "Bitey", "Stompy", "Hairy"};
        int i = randomizer.randomIntInRange(0, names.length - 1);
        return names[i];
    }

    /** Pick a random dragonling name. **/
    private String pickDragonlingName(){
        String names[] = new String[]{"Stonegatherer", "Flowerguardian", "Goldpolisher", "Silvercollector",
                                        "Bronzebearer", "Forkfinder", "Spoonsifter", "Shoekeeper",
                                        "Swordsharpener", "Shieldkeeper", "Goatmonarch", "Stickfinder",
                                        "Jewelhunter", "Wormguardian", "Armoramasser", "Pillowkeeper",
                                        "Cheesethief", "Eggthief", "Milkthief", "Breadthief", "Horsestealer",
                                        "Dogstealer", "Catburglar", "Pebblemaster", "Carrotkeeper",
                                        "Berrypicker", "Horselord"};
        int i = randomizer.randomIntInRange(0, names.length - 1);
        return names[i];
    }

}
