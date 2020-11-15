package dnd.weapons;

public class Daggers extends Weapon {

    public Daggers(){
        this.name = "Daggers";
        this.desc = "A pair of twin jagged daggers with finely-fashioned hilts.";
        this.playerUsageString = "Twin blades flash in the light as you lunge and attack with your daggers!";

        this.damageDie = 8;
        this.ability = "dex";
    }

    public String getUsage(int n){
        String usage = "";

        if (n == 1)
            usage = "Quick as lightning, you slash with both daggers!";
        else if (n == 2)
            usage = "Your daggers slice through the air as you strike!";
        else if (n == 3)
            usage = "You give your daggers a quick whirl before darting forward with a deadly attack!";
        else
            usage = this.playerUsageString;

        return usage;
    }

}
