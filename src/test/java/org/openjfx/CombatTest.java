package org.openjfx;


import dnd.characters.*;
import dnd.characters.Character;
import dnd.combat.Combat;
import dnd.combat.MonsterGenerator;
import dnd.monsters.Goblin;
import dnd.weapons.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

//Contains all of the tests to ensure that character generation of various races works
public class CombatTest {
    //This function checks to see if two objects are equal to one another
    public boolean objectEquals(String str, Object o, Object e){
        if(e.getClass() == o.getClass()){
            return true;
        }
        else{
            System.err.println(str);
            return false;}
    }

    public Character player = new Character("Jim");

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Tests that combat is successfully created.
     */
    @Test
    public void testCombatCreation() {
        System.out.println("\n\n******\nTesting Combat Creation");
        Combat c = new Combat(player);
        System.out.println("Type of monster: " + c.getOpponentType());
        System.out.println("Monster name: " + c.getOpponentName());
        c.opponent = new Goblin("Andy", player);

        assertEquals("Monster name should be Andy", "Andy", c.getOpponentName());
        assertEquals("Monster type should be Goblin", true, objectEquals("Should be Goblin", c.opponent, new Goblin("Andy", player)));
    }

    /**
     * Tests that combat outcomes correctly detect end conditions and player win/loss.
     */
    @Test
    public void testCombatOutcomes(){
        System.out.println("\n\n******\nTesting Combat Outcomes");
        Combat c = new Combat(player);
        assertEquals("checkForWin() should return false at the start of new combat", false, c.checkForWin());

        c = new Combat(player);
        player.setHitPoints(10);
        c.opponent.setHitPoints(0);
        assertEquals("checkForWin() should return true", true, c.checkForWin());
        assertEquals("The playerVictory should be true", true, c.playerVictory);
        System.out.println("Combat outcome String should indicate a player victory: \n" + c.getOutcomeString());

        c = new Combat(player);
        player.setHitPoints(0);
        c.opponent.setHitPoints(10);
        assertEquals("checkForWin() should return true", true, c.checkForWin());
        assertEquals("The playerVictory should be false", false, c.playerVictory);
        System.out.println("Combat outcome String should indicate a player defeat: \n" + c.getOutcomeString());
    }

}
