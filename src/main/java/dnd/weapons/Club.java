package dnd.weapons;

public class Club extends Weapon {

    public Club(){
        this.name = "Club";
        this.desc = "A heavy club. Watch out for splinters!";
        this.playerUsageString = "With great effort, you strike with the club!";

        this.damageDie = 8;
        this.ability = "str";
    }

    public String getUsage(int n){
        String usage = "";

        if (n == 1)
            usage = "Your arms burn as you rear the club back then bring it down in one swoop!";
        else if (n == 2)
            usage = "With a yell, you attack with your club!";
        else if (n == 3)
            usage = "In an impressive feat of strength, you lift your club into the air and swing!";
        else
            usage = this.playerUsageString;

        return usage;
    }

}
