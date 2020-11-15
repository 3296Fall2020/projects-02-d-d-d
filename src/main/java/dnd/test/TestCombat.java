package dnd.test;

import dnd.combat.Combat;
import dnd.monsters.*;
import dnd.weapons.*;
import dnd.characters.Character;

public class TestCombat {

    /** A method for testing combat and outputting results to the terminal. **/
    public void test() {

        Character c = new Character("Jim", "human");
        //c.setHitPoints(50);

        for (int i = 0; i < 15; i++)
            c.addXP(300);


        //for (int i = 0; i < 24; i++) {
            Combat combat = new Combat(c);
            combat.runCombat();
            c.addXP(100);
        //}

        //Combat combat = new Combat(c);
        //combat.runCombat();
    }

}
