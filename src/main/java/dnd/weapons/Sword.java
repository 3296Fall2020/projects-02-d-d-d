package dnd.weapons;

public class Sword extends Weapon {

    public Sword(){
        this.name = "Sword";
        this.desc = "A fine blade. Be careful! It's sharp.";
        this.playerUsageString = "Your blade glints wickedly in the light as you strike a blow!";

        this.damageDie = 6;
        this.ability = "str";
    }

    public String getUsage(int n){
        String usage = "";

        if (n == 1)
            usage = "Hands braced around the hilt of your sword, you lunge forward and strike!";
        else if (n == 2)
            usage = "You bring down your sword in one gleaming, deadly arc!";
        else if (n == 3)
            usage = "You point your blade towards your target to mark them before rushing to attack!";
        else
            usage = this.playerUsageString;

        return usage;
    }

}
