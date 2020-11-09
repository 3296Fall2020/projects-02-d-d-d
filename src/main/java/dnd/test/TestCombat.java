package dnd.test;

import dnd.combat.Combat;
import dnd.monsters.*;
import dnd.weapons.*;
import dnd.characters.Character;

public class TestCombat {

    /** A method for testing combat and outputting results to the terminal. **/
    public void test() {

        Character c = new Character("Jim");
        c.setHitPoints(1000);

        Combat combat = new Combat(c);
        combat.runCombat();
    }

}
