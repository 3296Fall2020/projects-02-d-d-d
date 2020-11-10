package dnd.characters;

import dnd.dice.Dice;

import java.util.Random;


//The purpose of this character class is to allow user to customize their characters with options such as stats and naming
public class Character {
    Dice die = new Dice();

    String name; //name of character

    //Array of six integers to represent Six ability scores that define each character, usually given between 3 and 18
    //basis for every d20 roll
    //Certain scores races increase certain abilities, and each class has abilities they consider very important.
    int[] abilities;
    //index 0   1           2               3            4       5
    //strength, dexterity, constitution, intelligence, wisdom, charisma


    //In D&D, the ability modifier for each ability is used often in rolling d20 and is derived from the activity score.
    int[] abilityModifier;

    //In D&D, players gain experience points (XP) and can level up. By default, players start at level one with 0 XP.
    // A higher level character starts with the XP required to reach that level.
    // For the purposes of this game, we will say that a new level is obtained when player gains 300 more points from previous level
    int level;
    int XP;
    private static final int XPIncrement = 300;

    //get class membership
    public String getClassMembership() {
        return classMembership;
    }

    //set class membership
    public void setClassMembership(String classMembership) {
        this.classMembership = classMembership;
    }

    //In D&D, characters have hit points that define how tough they are in combat, and this is defined by hit die specific to their class.
    //Players start with hit points equal to highest roll of that dice, as indicated by class description.
    int hitPoints;
    private static final int hitPointInitial = 15;

    //In D&D, every character belongs to a race, and many subraces exist.
    //Most common races include: dwarves, elves, halflings, and humans
    //Each race has a starting language and base speed.
    String race;
    String language;
    int speed;

    //In D&D, each character belongs to a class that describes their vocation, special talents,and tactics they are most likely to employ.
    //Depending on the class, characters receive class features unique to their class and proficiencies like skills, weapons, tools, etc.
    String classMembership;

    //return hit points of character
    public int getHitPoints() {
        return hitPoints;
    }

    //set hit points of character
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /*
     * This function generates the six ability scores randomly.
     * It simulates rolling four 6-sided dice and recording the total of the
     * highest three dice on a piece of scratch paper. We five
     * more times, so that we have six numbers, one for each of the six abilities.
     * Populates abilities array with number for each ability (before race is accounted for).
     * For purposes of this app, users will not be able to choose which ability score goes to which ability.
     */
    public void generateAbilityScores() {
        //For each ability
        for (int i = 0; i < abilities.length; i++) {
            //results of 4 dice
            int[] dice = die.roll(6,4);
            int discardedDice = pickLowest(dice); //returns index of discarded dice
            this.abilities[i] = (dice[0] + dice[1] + dice[2] + dice[3]) - dice[discardedDice];
        }
    }

    /*
     * This function determines the ability modifier by subtracting ten from the ability score and dividing the result by 2.
     */
    public void determineAbilityModifier() {
        for (int i = 0; i < abilities.length; i++) {
            this.abilityModifier[i] = ((this.abilities[i] - 10) / 2);
        }
    }


    //get name of character
    public String getName() {
            return name;
        }

    //set name of character
    public void setName(String name) {
        this.name = name;
    }


    /*
     * When a character is first generated, they are able to choose their name and are given
     * a randomly determined ability score and ability modifiers for strength, dexterity, constitution, intelligence,
     * wisdom, and charisma.
     * By default, they start at level 1 with 0 XP.
     */
    public Character(String name) {
        this.name = name;
        this.abilities = new int[6];
        generateAbilityScores();
        this.abilityModifier = new int[6];
        determineAbilityModifier();
        this.level = 1;
        this.XP = 0;

    }

    /*
     * When a character is first generated, they are able to choose their name and are given
     * a randomly determined ability score and ability modifiers for strength, dexterity, constitution, intelligence,
     * wisdom, and charisma.
     * By default, they start at level 1 with 0 XP and 15 hit points.
     */
    public Character(String name, String race) {
        this.name = name;
        this.abilities = new int[6];
        generateAbilityScores();
        this.abilityModifier = new int[6];
        determineAbilityModifier();
        this.level = 1;
        this.XP = 0;
        this.race = race;
        setRaceVar();
        this.hitPoints = hitPointInitial;
    }

    /*
     * This function determines the lowest dice of those rolled and returns its index.
     */
    public int pickLowest(int[] dice) {
        //Be default, the first dice is the lowest
        int lowest = 0;
        for (int i = 0; i < dice.length; i++) {
            //if the dice[i] is lower than the lowest dice, dice[i] is now the lowest
            if (dice[i] < dice[lowest]) {
                lowest = i;
            }
        }
        return lowest;
    }

    //get current level of character
    public int getLevel() {
        return level;
    }

    //set current level of character
    public void setLevel(int level) {
        this.level = level;
    }

    //get XP points of character
    public int getXP() {
        return XP;
    }

    //set abilities
    public void setAbilities(int[] abilities) {
        this.abilities = abilities;
    }

    //set ability mod
    public void setAbilityModifier(int[] abilityModifier) {
        this.abilityModifier = abilityModifier;
    }

    //set race
    public void setRace(String race) {
        this.race = race;
    }

    //set language
    public void setLanguage(String language) {
        this.language = language;
    }

    //set speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //add XP points to character's XP total
    //returns XP needed to get to next level
    public int addXP(int XP) {
        this.XP += XP;
        // XP that deems that player has advanced a level
        // For example, if player is on level 1 and wants to be on level 2, they need 300 total XP
        // So the XP needed for the next level is 1*300
        int XPofNextLevel = level*XPIncrement;
        if (this.XP >= XPofNextLevel) {
            //level up if necessary, and also increase hit points by dice.roll(10,3) + constitutionModifier
            this.level += 1;
            int HPIncrease = die.rollSum(10,3) + getConstitutionMod();
            this.hitPoints += HPIncrease;
            System.out.println("\nCongrats! You advanced to level " + getLevel());
            XPofNextLevel = getLevel() *XPIncrement;
        }
        return (XPofNextLevel - this.XP);
        //returns amount needed for next level

    }

    //getter method for strength
    public int getStrength() {
        return this.abilities[0];
    }

    //getter method for dexterity
    public int getDexterity() {
        return this.abilities[1];
    }

    //getter method for constitution
    public int getConstitution() {
        return this.abilities[2];
    }

    //getter method for intelligence
    public int getIntelligence() {
        return this.abilities[3];

    }

    //getter method for wisdom
    public int getWisdom() {
        return this.abilities[4];
    }

    //getter method for charisma
    public int getCharisma() {
        return this.abilities[5];
    }

    //getter method for strength
    public int getStrengthMod() {
        return this.abilityModifier[0];
    }

    //getter method for dexterity
    public int getDexterityMod() {
        return this.abilityModifier[1];
    }

    //getter method for constitution
    public int getConstitutionMod() {
        return this.abilityModifier[2];
    }

    //getter method for intelligence
    public int getIntelligenceMod() {
        return this.abilityModifier[3];

    }

    //getter method for wisdom
    public int getWisdomMod() {
        return this.abilityModifier[4];
    }

    //getter method for charisma
    public int getCharismaMod() {
        return this.abilityModifier[5];
    }

    //getter method for race
    public String getRace() {
        return race;
    }

    //getter method for language
    public String getLanguage() {
        return language;
    }

    //getter method for speed
    public int getSpeed() {
        return speed;
    }

    /*
     * This method sets speed, language, and race variables of character based on race given when creating Character object.
     */
    public void setRaceVar() {
        String lc = this.race.toLowerCase();
        //Humans speak Common, have a base walking speed of 30 feet, and all ability scores increase by one.
        if (lc.equals("human")) {
            this.race = "Human";
            this.language = "Common";
            this.speed = 30;
            for (int i = 0; i < abilities.length; i++) {
                this.abilities[i] += 1;
            }
        } else if (lc.equals("elf")) {
        //Elves speak Common and Elvish, have a base walking speed of 30 feet, and dexterity score increases by 2
            this.race = "Elf";
            this.language = "Elvish & Common";
            this.speed = 30;
            this.abilities[1] += 2;
        } else if (lc.equals("dwarf")){
        //Dwarf speak Dwarfish and Common, have a base walking speed of 25 feet, and constitution score increases by 2
            this.race = "Dwarf";
            this.language = "Dwarfish & Common";
            this.speed = 25;
            this.abilities[2] += 2;
        } else {
        //otherwise, race is halfling
        //Halflings speak Hafling and Common, have a base walking speed of 25 feet, and dexterity score increases by 2
            this.race = "Halfling";
            this.language = "Halfling and Common";
            this.speed = 25;
            this.abilities[1] +=2;
        }
    }
}
