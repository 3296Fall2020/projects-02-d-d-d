package dnd.test;

import dnd.combat.Combat;
import dnd.monsters.*;
import dnd.weapons.*;

public class TestCombat {

    /** A method for testing combat and outputting results to the terminal. **/
    public void test_combat() {

        Combat combat = new Combat();
        combat.initializeCombat();
        combat.runCombat();

    }

}
