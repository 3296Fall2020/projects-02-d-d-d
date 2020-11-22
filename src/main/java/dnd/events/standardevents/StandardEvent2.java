package dnd.events.standardevents;

import dnd.characters.Character;
import dnd.events.Event;

public class StandardEvent2 extends Event {

    private String animal = getRandomSmallAnimal();
    private String color = getRandomColor();
    private String impression = getRandomImpression();

    /** Neutral events are events that simply occur, and may or may not have an effect on the player. **/
    public StandardEvent2(Character player) {
        super(player);
    }

    public void buildDescription(){
        String desc = "You're traveling the well-worn path when suddenly, you hear soft rustling coming from your right. ";

        if (player.getWisdom() >= 13 || player.getClassMembership() == "Rogue")
            desc += "Luckily, you're perceptive enough to notice it and halt in your tracks, brandishing your " + player.getWeapon().getName() + ".";
        else
            desc += "For a few happy moments, you don't notice a thing, too focused on the path ahead. But then a particularly " +
                    "loud 'crunch!' of a twig finally draws your attention. You whirl around to confront the...bushes? ";

        newParagraph();
        desc += "\"Who's there!\" you ";

        if (player.getCharisma() >= 14)
            desc += "call out, unhesitating. ";
        else if (player.getCharisma() >= 11)
            desc += "ask, managing to shake off the urge to pause. ";
        else
            desc += "stammer, gripping your " + player.getWeapon().getName() + " warily.";

        desc += newParagraph();
        desc += "No response. ";

        desc += newParagraph();
        desc += "The bush shakes and rustles once again. There's definitely something there! ";

        this.setEventDescription(desc);
    }

    public void buildButtons(){
        this.setButtonAText("Attack!");

        this.setButtonBText("Look inside");

        this.setButtonCText("Run away");
    }

    public String chooseA() {
        String ret = "";

        if (player.getClassMembership() == "Fighter")
            ret += "You're a fighter, which means that you face your problems head-on, and this is no exception. ";
        else if (player.getHitPoints() <= 10)
            ret += "You're already feeling pretty hurt, and you won't risk being bested by a surprise attack. ";
        else
            ret += "You decide that if anyone's going to strike, you'd rather have the element of surprise on your side. ";

        ret += newParagraph();
        ret += player.getWeapon().getUsage(dice.roll(4));

        ret += newParagraph();
        if (dice.roll(20) + player.getPlayerWeaponMod() >= 14)
            ret += "You manage to hit the bush! ";
        else
            ret += "But you fumble at the last second and end up attacking the poor ground instead... Close enough! ";

        ret += newParagraph();
        ret += "There's a startled sound from the bushes, more frenzied rustling, and then a streak of " + getRandomColor() + " dart " +
                "out of the bushes! Unfortunately, it's gone before you can get a good look at what it was.";

        ret += failEvent();

        return ret;
    }

    public String chooseB() {
        String ret = "Right, right. It's best not to act too rashly. ";

        if (player.getAlignment().contains("good"))
            ret += "Besides, you can't risk hurting an innocent person! ";
        else
            ret += "You don't want to draw unnecessary attention to yourself. ";

        ret += "You take one cautious step forward and reach out with your " + player.getWeapon().getName() + ", using " +
                "it to part the frayed bushes the rest of the way. ";

        ret += newParagraph();
        ret += "There! You see a flash of " + color + ". What could it be?";

        ret += newParagraph();
        ret += "Drawing closer as slowly as possible, you finally get a good look at the so-called threat: it's a little " +
                color + " " + animal + "! It blinks up at you. It looks unharmed where it has nestled too deep in the " +
                "bush for your " + player.getWeapon().getName() + " to reach, but it does look " + impression + ". ";

        ret += newParagraph();
        ret += "Before you can decide what to do next, it darts out of the bush and into the surrounding forest. However, " +
                "the impression of its eyes, strangely vivid for an animal, stays with you long after it has gone. ";

        ret += passEvent();

        return ret;
    }

    public String chooseC() {
        String ret = "Whatever's hiding in there, it's none of your business nor any business you would willingly involve " +
                "yourself in. You decide to hurry on along.";

        return ret;
    }

    public String passEvent() {
        String ret = "";

        if (player.getWisdom() <= 10){
            ret += "You feel like the " + animal + " has imparted some of its wisdom to you before it left. You gained " +
                    "+1 to your Wisdom!";
            player.setWisdom(player.getWisdom() + 1);
        }
        else if (randomizer.randomIntInRange(1, 5) <= 3){
            ret += "You feel like you've learned something. You're not sure what, but it's definitely something! ";
            int xpEarned = randomizer.randomIntInRange(10, 15);
            ret += "You earned " + xpEarned + " XP!";
            player.addXP(xpEarned);
        }
        else{
            ret += "You definitely hadn't expected to encounter anything like that " + animal + " today, but ";
            if (player.getAlignment().contains("good"))
                ret += "maybe you've made a new friend.";
            else
                ret += "maybe you've made a potential ally for...someday.";
        }

        return ret;
    }

    public String failEvent() {
        String ret = "";

        // no reward or consequence

        return ret;
    }
}
