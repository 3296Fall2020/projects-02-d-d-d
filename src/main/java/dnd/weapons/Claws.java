package dnd.weapons;

public class Claws extends Weapon {

    public Claws(){
        this.name = "Claws";
        this.desc = "A spider's claws. You could probably whittle these down and fashion a gauntlet out of them.";
        this.playerUsageString = "With a great swipe, you slash out with the claws!";

        this.damageDie = 6;
        this.ability = "dex";
    }

    public String getUsage(int n){
        String usage = "";

        if (n == 1)
            usage = "With a great swipe, you slash out with the claws!";
        else if (n == 2)
            usage = "You deftly swipe out with your claws!";
        else if (n == 3)
            usage = "You slash your claws with a dexterity a spider would envy!";
        else
            usage = this.playerUsageString;

        return usage;
    }
}
