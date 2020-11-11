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
            usage = "With a great swipe, you slash out with the claws!";
        else if (n == 2)
            usage = "Your arm blurs in the air as you swipe out with your claws!";
        else if (n == 3)
            usage = "You slash your claws with a dexterity a spider would envy!";
        else
            usage = this.playerUsageString;

        return usage;
    }

}
