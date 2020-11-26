package dnd.events.standardevents;

import dnd.characters.Character;
import dnd.events.StandardEvent;

public class StandardEvent1 extends StandardEvent {

    private int hpRegained = dice.roll(4) + player.getConstitutionMod();
    private String color = getRandomColor();

    /** Standard events are events that simply occur, and may or may not have an effect on the player. **/
    public StandardEvent1(Character player) {
        super(player);
    }

    public void buildDescription(){
        String desc = "You've begun to lose track of how long you've been walking, but soon you come across " +
                "a quiet pond surrounded by clumps of weeds and clusters of small, " + color + " spotted " +
                "mushrooms. There's a strange thickness in the air, almost sickly sweet.";

        desc += newParagraph();
        desc += "You feel uneasy, the way you feel ";

        if (player.getClassMembership() == "Fighter")
            desc += "in the downswing of a battle, when your " + player.getWeapon().getName().toLowerCase() + " " +
                    "has turned into a heavy weight in your hands yet you can't put it down, afraid that " +
                    "there is still more to come. ";
        else if (player.getClassMembership() == "Wizard")
            desc += "in the aftermath of a spell that has been cast, when traces of magic still linger in " +
                    "the air, faint but almost palpable. A reminder of the power you are capable of. ";
        else if (player.getClassMembership() == "Rogue")
            desc += "amidst a hunt, flurrying between foliage, set on your mark while all too aware of the one " +
                    "on your own back. The way you feel when you aren't so sure you're the mark of someone else's hunt. ";
        else
            desc += "when you lay a hand upon an ache, a cut, a burn. That fleeting sense of uncertainty, that slightest " +
                    "wobble in the pillars that uphold your faith, that 'what if': what if this, finally, is too " +
                    "much for you? ";

        desc += newParagraph();
        desc += "You don't feel unsafe here, only...cautious.";

        this.setEventDescription(desc);
    }

    public void buildButtons(){
        this.setButtonAText("Drink from the pond");

        this.setButtonBText("Eat a mushroom");

        this.setButtonCText("Leave the pond alone");
    }

    public String chooseA() {
        String ret = "You walk up to the pond and kneel by its side. The water looks clean, clean enough that you " +
                "can see all the way through to the stone-strewn bottom of the pond, but strangely you can't " +
                "see your reflection on the surface.";

        ret += newParagraph();
        ret += "Carefully, you bend down, dip cupped hands into the water, and drink.";

        ret += newParagraph();
        ret += "The water tastes " + getRandomTaste() + " at first, but after a few seconds, you realize that it leaves a" +
                "more noticeable, metallic tang in the back of your mouth. ";

        ret += newParagraph();
        ret += "You stand back up - then wince as your stomach suddenly cinches. The pain is quickly gone; it's as " +
                "if someone pinched your side. You don't notice anything else... (For now?)";

        ret += newParagraph();
        ret += "You should probably move on.";

        ret += failEvent();

        return ret;
    }

    public String chooseB() {
        String ret = "Those " + color + " mushrooms look appetizing, and you wouldn't mind having a quick snack. ";
        if (player.getIntelligence() >= 14)
            ret += "You also recognize that these mushrooms aren't one of the many poisonous ones that populate " +
                    "the wild. ";
        if (player.getClassMembership() == "Rogue")
            ret += "You think you've even eaten this type of mushroom before in one of your travels. ";

        ret += newParagraph();
        ret += "You kneel down by the edge of the pond and";
        if (player.getDexterity() >= 12)
            ret += " carefully pluck ";
        else
            ret += ", after several tugs, manage to dig out ";
        ret += "one of the mushrooms. It's small enough to fit in your mouth in one bite. ";

        ret += newParagraph();
        ret += "It tastes " + getRandomTaste() + ". A little puzzled, you climb back to your feet. Somehow, you feel " +
                "a little stronger than before. Better keep moving.";

        ret += passEvent();

        return ret;
    }

    public String chooseC() {
        String ret = "You don't really have a good feeling about this place. ";

        if (player.getWisdom() >= 12)
            ret += "You also figure that it's probably best not to eat or drink from a strange place in the woods. ";

        ret += newParagraph();
        ret += "Sparing the water one last look, you continue your path around it, and soon the glimmering pond " +
                "and its little " + color + " mushrooms disappear behind you. ";

        return ret;
    }

    public String passEvent() {
        String ret = newParagraph() + "You regained " + hpRegained + " HP! ";

        this.player.setHitPoints(player.getHitPoints() + hpRegained);

        return ret;
    }

    public String failEvent() {
        String ret = newParagraph();
        if (player.getHitPoints() < 5){
            ret += "Luckily, you didn't take any damage. ";
        }
        else{
            int hpLost = dice.roll(4);
            this.player.setHitPoints(player.getHitPoints() - hpLost);
            ret += "You took " + hpLost + " damage! ";
        }

        return ret;
    }
}
