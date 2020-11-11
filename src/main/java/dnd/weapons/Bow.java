package dnd.weapons;

public class Bow extends Weapon {

    public Bow(){
        this.name = "Bow";
        this.desc = "A bow";
        this.playerUsageString = "You swiftly notch an arrow and fire!";

        this.damageDie = 8;
        this.ability = "dex";
    }

    public String getUsage(int n){
        String usage = "";

        if (n == 1)
            usage = "You fire an arrow, sending it hurtling through the air!";
        else if (n == 2)
            usage = "You take a deep breath, steady your sights, then loose an arrow!";
        else if (n == 3)
            usage = "Carefully, you draw back your bow, aim, then...release!";
        else
            usage = this.playerUsageString;

        return usage;
    }

}
