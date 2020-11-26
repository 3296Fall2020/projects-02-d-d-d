package dnd.events.standardevents;

import dnd.characters.Character;
import dnd.events.Event;

public class StandardEvent4 extends Event {

    /** Standard events are events that simply occur, and may or may not have an effect on the player. **/
    public StandardEvent4(Character player) {
        super(player);
    }

    public void buildDescription(){
        String desc = "";

        if (player.getIntelligence() >= 12)
            desc += "Although you can tell by the height of the sun in the sky that it should be no later than midday, ";
        else if (player.getWisdom() >= 12)
            desc += "Although your instincts tell you that the day had only just passed its peak, ";
        else
            desc += "Although you thought you were walking with great attention to your surroundings, ";

        desc += "you soon find the sky quickly darkening around you.";

        desc += newParagraph();
        desc += "Around you?";

        desc += newParagraph();
        desc += "That's definitely not right. As your surroundings begin to grow darker and darker, you realize that it " +
                "isn't because of the sky, but instead because of a heavy black cloud growing around you!";

        this.setEventDescription(desc);
    }

    public void buildButtons(){
        this.setButtonAText("Shout for help");

        this.setButtonBText("Run out of the fog");

        this.setButtonCText("Hold your breath");
    }

    public String chooseA() {
        String ret = "Someone has to be nearby! You shout for help, but you quickly find that it feels more like " +
                "shouting into an empty bottle, your voice bouncing off of the walls of some unseen barrier and " +
                "merely echoing around you. ";

        ret += newParagraph();
        ret += "You try to shout again, but a sudden fatigue overtakes you...";

        ret += newParagraph() + "..." + newParagraph() + "..." + newParagraph() + "...";

        ret += newParagraph();
        ret += "You wake up unharmed, curled up at the base of a tree. Was it just a nightmare? You feel a little " +
                "different, though...";

        ret += failEvent();

        newParagraph();
        ret += "You brush yourself off and stand up. You should get going before you \"fall asleep\" again!";

        return ret;
    }

    public String chooseB() {
        String ret = "";

        if (player.getClassMembership() == "Cleric")
            ret += "The growing heaviness of your limbs and the thickness of the air worries you; you know that " +
                    "there are harmful poisons and toxins that could have the same (and deadlier) effects! " + newParagraph();

        ret += "You can't just stand around and wait to see what the fog will do. You begin to hold your breath " +
                "and run forward, in the last direction of the road you remember. ";

        newParagraph();
        if (player.getDexterity() >= 12 && player.getConstitution() >= 12){
            ret += "You run for what seems like forever - you don't remember the road being this long! - until your " +
                    "legs and lungs begin to burn. Just as the effort is finally about to take its toll on you, " +
                    "you break free of the darkness and into the bright sunlight!";

            ret += newParagraph();
            ret += "Panting for breath, you look behind you. There's no evidence of what just happened except your " +
                    "footprints in the dirt. What on earth was that? ";

            ret += newParagraph();
            ret += "Deciding you don't want to wait for it to happen again, you move on. ";

            ret += passEvent();
        }
        else{
            ret += "Unfortunately, you only make it a few steps before the heaviness claims your legs too. They " +
                    "buckle beneath you, and the last thing you remember thinking is how nice it would be to take " +
                    "a quick nap...";

            newParagraph();
            ret += "You jolt awake. ";

            newParagraph();
            ret += "You are sitting in the shade of a roadside tree, perfectly unharmed. A dream? But then why do you " +
                    "feel a little different? ";

            ret += failEvent();

            newParagraph();
            ret += "You should get going before anything else happens!";
        }

        return ret;
    }

    public String chooseC() {
        String ret = "";

        if (player.getClassMembership() == "Wizard")
            ret += "You've studied spells that can create clouds of darkness like this before... If it really is " +
                    "magic, you know that there would be no use trying to outrun it. " + newParagraph();

        ret += "You force yourself to calm down and drop to a crouch, eyes closed and head in your hands. You suck in " +
                "one last breath, and then hold it...";

        ret += newParagraph() + "and hold it..." + newParagraph() + "and hold it...";

        if (player.getConstitution() >= 14) {
            ret += "...and hold it. ";

            newParagraph();
            ret += "Amazingly, you manage to do it! When you next open your eyes, the darkness has cleared, and you are " +
                    "crouching in the middle of the road. Whoops! You quickly move out of the way. ";

            newParagraph();
            ret += "You glance behind you to see what could have caused that strange event, but there's no one else here " +
                    "except you and the trees. ";

            ret += passEvent();
        }
        else {
            ret += "and... ";

            newParagraph();
            ret += "You black out. ";

            ret += newParagraph() + "..." + newParagraph() + "..." + "...";

            ret += newParagraph();
            ret += "You wake up with a gasp, remembering too late that you're supposed to be holding your breath--! ";

            ret += newParagraph();
            ret += "Oh. But it's not dark anymore. You're sitting under a tree, and the road is clear, and the sun " +
                    "is shining above you... Was it all just a dream? But you feel a little different. ";

            ret += failEvent();

            ret += newParagraph();
            ret += "You should go before anything weird happens again! ";
        }

        return ret;
    }

    public String passEvent() {
        String ret = newParagraph();

        int xpGain = dice.rollSum(2, 10);
        ret += "You gained " + xpGain +"! ";
        player.addXP(xpGain);

        return ret;
    }

    public String failEvent() {
        String ret = newParagraph() + "You lost ";

        int effect = dice.roll(4);
        if (effect == 1) {
            player.setConstitution(player.getConstitution() - 1);
            ret += "1 point of Constitution!";
        }
        else if (effect == 2){
            player.setIntelligence(player.getDexterity() - 1);
            ret += "1 point of Dexterity!";
        }
        else if (effect == 3){
            player.setWisdom(player.getWisdom() - 1);
            ret += "1 point of Wisdom!";
        }
        else{
            player.setIntelligence(player.getIntelligence() - 1);
            ret += "1 point of Intelligence!";
        }

        return ret;
    }
}