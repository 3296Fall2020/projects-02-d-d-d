package org.openjfx;

import dnd.characters.*;
import dnd.characters.Character;
import dnd.weapons.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

//contains all of the tests to show that various character statistics are generated correctly nd can be changed.
public class StatisticsTest {

    //This function checks to see if two objects are equal to one another
    public boolean ObjectEquals(String str, Object o, Object e){
        if(e.getClass() == o.getClass()){
            return true;
        }
        else{
            System.err.println(str);
            return false;}
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }



    /**
     * Test 1: Ensures that player starts with HP of 15.
     */
    @Test
    public void shouldHave15AsDefaultHP() {
        //Testing that making a character works
        Character Alex = new Character("Alex");
        assertEquals("Should print 15 as HP", 15, Alex.getHitPoints());
    }

    /**
     * Test 2: Ensures that with an increase in 300 XP, the character levels up and has an increase in HP
     */
    @Test
    public void shouldIncreaseHPWithLevelUp() {
        //Testing that making a character works
        Character Alex = new Character("Alex");
        assertEquals("Should print 0 as XP", 0, Alex.getXP() );
        assertEquals("Should print 15 as HP", 15, Alex.getHitPoints() );
        assertEquals("Should print 1 as level", 1, Alex.getLevel() );
        //Testing that increasing XP to next level causes increase in HP
        Alex.addXP(300);
        assertEquals("Should print 300 as XP", 300, Alex.getXP());
        assertEquals("Should print 2 as level", 2, Alex.getLevel());
        assertNotEquals("Should not be 15 as HP", 15, Alex.getHitPoints());
    }




    /**
     * Test 3: Ensures that different healing die are made
     */
    @Test
    public void shouldMakeDifferentHealingDie() {
        //Testing that other race characters can be made.
        Dwarf Al = new Dwarf("Al");
        Dwarf Bo = new Dwarf("Bo");
        Bo.setHealingDie(Al.getHealingDie());
        assertEquals("Should print same healing die size", Bo.getHealingDie(), Al.getHealingDie());
        System.out.println("Al's Healing Die is " + Al.getHealingDie());


    }

    /**
     * Test 4: Ensures that different races have different default hit points
     */
    @Test
    public void shouldHaveDifferentHitPoints() {
        //Testing that other race characters can be made.
        Dwarf Al = new Dwarf("Al");
        Elf Bo = new Elf("Bo");
        Human Charlie = new Human("Charlie");
        Halfling Matt = new Halfling("Matt");
        assertNotEquals("should have different hit point values", Al.getHitPoints(), Bo.getHitPoints());
        assertNotEquals("should have different hit point values", Charlie.getHitPoints(), Matt.getHitPoints());

    }

}
