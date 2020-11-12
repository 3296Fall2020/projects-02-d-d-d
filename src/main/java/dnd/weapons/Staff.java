package dnd.weapons;

public class Staff extends Weapon {

    public Staff(){
        this.name = "Staff";
        this.desc = "A sturdy staff shaped out of twining wood and crowned by a luminous orb of magic.";
        this.playerUsageString = "The air sings with magic as you aim your staff and fire!";

        this.damageDie = 8;
        this.ability = "wis";
    }

    public String getUsage(int n){
        String usage = "";

        if (n == 1)
            usage = "Blazing gold-fire light bursts forth from your staff and spindles towards your target!";
        else if (n == 2)
            usage = "With a shout, you drive your staff into the ground, sending out a powerful pulse of magic!";
        else if (n == 3)
            usage = "Your staff thrums with energy as you tip it towards your target and loose a bolt of magic!";
        else
            usage = this.playerUsageString;

        return usage;
    }

}
