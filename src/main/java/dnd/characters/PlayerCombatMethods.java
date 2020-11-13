package dnd.characters;

import dnd.weapons.Weapon;

public class PlayerCombatMethods {

    //variable for the player's weapon
    public Weapon weapon;

    /** Get the player's equipped weapon. **/
    public Weapon getWeapon(){
        return this.weapon;
    }

    /** Set the player's equipped weapon. **/
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    /** Check which ability the player weapon relies on and return that ability's modifier. **/
    public int getPlayerWeaponMod(){
        String ability = weapon.getAbility().toLowerCase();

        if (ability == "str")
            return this.getStrengthMod();
        else if (ability == "dex")
            return this.getDexterityMod();
        else if (ability == "con")
            return this.getConstitutionMod();
        else if (ability == "int")
            return this.getIntelligenceMod();
        else if (ability == "wis")
            return this.getWisdomMod();
        else
            return this.getCharismaMod();
    }

}
