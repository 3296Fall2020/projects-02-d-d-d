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

        //pick a type of monster randomly (modify as necessary)
        if(n <= 4)
            return new Spider(pickSpiderName(), player);
        else if (n <= 8)
            return new Goblin(pickGoblinName(), player);
        else if (n <= 12)
            return new Bandit(pickBanditName(), player);
        else if (n <= 15)
            return new CorruptedMage(pickMageName(), player);
        else if (n <= 18)
            return new Orc(pickOrcName(), player);
        else
            return new Dragonling(pickDragonlingName(), player);
    }

    /** Pick a random spider name. **/
    private String pickSpiderName(){
        String names[] = new String[]{"Ssamara", "Ssamuel", "Sussan", "Anssela", "Rissard", "Sserafina",
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
                                        "Pif", "Prru", "Jak", "Groo", "Euck", "Chur", "Glug", "Zzit", "Yulk",
                                        "Jud", "Ree", "Bor", "Sik", "Huf", "Pah", "Mmuf", "Tin", "Ayd", "Atch",
                                        "Huk", "Fah", "Guhh", "Gra", "Paah", "Chek", "Tsik", "Druk"};
        int i = randomizer.randomIntInRange(0, names.length - 1);
        return names[i];
    }

    /** Pick a random orc name. **/
    private String pickOrcName(){
        String names[] = new String[]{"Tiny", "Fluffy", "Grumpy", "Sleepy", "Shorty", "Silly", "Bumpy",
                                        "Smarty", "Speedy", "Happy", "Grumbly", "Chewy", "Crunchy",
                                        "Heavy", "Stinky", "Icky", "Drooly", "Weepy", "Growly", "Gooey",
                                        "Cheesy", "Friendly", "Skippy", "Lazy", "Smacky", "Boomy", "Huffy",
                                        "Giddy", "Jumpy", "Curly", "Bitey", "Stompy", "Hairy", "Goopy"};
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
                                        "Berrypicker", "Horselord", "Planedevourer", "Boneguardian"};
        int i = randomizer.randomIntInRange(0, names.length - 1);
        return "The " + names[i];
    }

    /** Pick a random bandit name. **/
    private String pickBanditName(){
        String names[] = new String[]{"Katja the Cunning", "Jo the Jubilant", "Leya the Liar", "Gus the Great",
                                        "Hennie the Hateful", "Won the Wicked", "Randall the Rancid", "Buck the Bully",
                                        "Destine the Dirteater", "Finny the Fabulous", "Sparky the Snake",
                                        "Pravi the Piercer", "Sebille the Swift", "Vox the Villain", "Tir the Terrible",
                                        "Jiah the Unjust", "Andy the Average", "Loren the Livid", "Shefali the Shadow",
                                        "Miles the Malicious", "Gaston the Grunt", "Holden the Horrible", "Elda the Eagle"};
        int i = randomizer.randomIntInRange(0, names.length - 1);
        return names[i];
    }

    /** Pick a random mage name. **/
    private String pickMageName(){
        String names[] = new String[]{"Aiden the Bronze", "Aileen the Violet", "Ren the Red", "Destine the Salmon",
                                        "Greta the Green", "Ulla the Grey", "Octavian the Pink", "Scilla the Yellow",
                                        "Vex the Vermillion", "Cole the Crimson", "Min the Lavender", "Spiegel the Black",
                                        "Lysa the Blue", "Lam the Obsidian", "Ellar the Indigo", "Andel the Void",
                                        "Sylvia the Ashen", "Helga the Cerulean", "Bekah the Lilac", "Jaskar the Rose",
                                        "Korifus the Flame", "Theodora the Mauve", "Bo the Vivid", "Shin the Pale"};
        int i = randomizer.randomIntInRange(0, names.length - 1);
        return names[i];
    }

}
