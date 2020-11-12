package dnd.weapons;

public class Wand extends Weapon {

    public Wand(){
        this.name = "Wand";
        this.desc = "A wand fashioned out of an ancient wood. Somehow, it seems to weigh just right in your grip.";
        this.playerUsageString = "You take a deep breath and draw forth your magic, channeling it through your wand!";

        this.damageDie = 8;
        this.ability = "int";
    }

    public String getUsage(int n){
        String usage = "";

        if (n == 1)
            usage = "A blazing cerulean streak of magic shoots out from your wand!";
        else if (n == 2)
            usage = "With a graceful flick of your wrist, you send a bolt of magic racing towards your target!";
        else if (n == 3)
            usage = "You feel magic coursing from your palm into your wand, lashing through its length and then ripping through the air!";
        else
            usage = this.playerUsageString;

        return usage;
    }

}
