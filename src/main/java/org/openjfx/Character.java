package org.openjfx;

//The purpose of this character class is to allow user to customize their characters with options such as stats and naming
public class Character {
    String name; //name of character
    int strength; //strength of character, from 1 to 100

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Character(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }


}
