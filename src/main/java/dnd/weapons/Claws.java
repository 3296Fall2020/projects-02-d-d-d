package dnd.weapons;

public class Claws extends Weapon {

    public Claws(){
        this.name = "Claws";
        this.desc = "A spider's claws. You could probably whittle these down and fashion a gauntlet out of them.";
        this.playerUsageString = "With a great swipe, you slash out with your claws!";

        this.damageDie = 6;
        this.ability = "dex";
    }

    public String getUsage(int n){
        String usage = "";

        if (n == 1)
            usage = "Your claws blur through the air as you strike!";
        else if (n == 2)
            usage = "In one swift, calculated move, you lunge forth with your claws bared!";
        else if (n == 3)
            usage = "You slash your claws in a single, precise strike!";
        else
            usage = this.playerUsageString;

        return usage;
    }
}
