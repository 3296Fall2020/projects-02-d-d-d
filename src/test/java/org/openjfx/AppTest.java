package org.openjfx;

import dnd.characters.Character;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class AppTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test 1: Ensures that Character can be generated with name and abilities
     */
    @Test
    public void shouldCreateCharacter() {
        //Testing that making a character works
        Character Alex = new Character("Alex", "Human");
        //want to make sure that random numbers are being produced
        System.out.println("The character's strength is " + Alex.getStrength());
        System.out.println("The character's dexterity is " + Alex.getDexterity());
        System.out.println("The character's constitution is " + Alex.getConstitution());
        System.out.println("The character's intelligence is " + Alex.getIntelligence());
        System.out.println("The character's wisdom is " + Alex.getWisdom());
        System.out.println("The character's charisma is " + Alex.getCharisma());
        //Ensure that we can get Human related variables
        assertEquals("Should print Alex as name ", "Alex", Alex.getName());
        assertEquals("Should print Human as race ", "Human", Alex.getRace());
        assertEquals("Should print Common as language", "Common", Alex.getLanguage());
        assertEquals("Should print 30 ft as speed", 30, Alex.getSpeed());
        assertEquals("Should print 0 as XP", 0, Alex.getXP() );
        assertEquals("Should print 1 as level", 1, Alex.getLevel() );





    }

    /**
     * Test 2: Ensures that a Halfling can be created
     */
    @Test
    public void shouldCreateHalfling() {
        //Testing that other race characters can be made.
        Character Al = new Character("Al", "Halfling");
        assertEquals("Should print Al", "Al", Al.getName());
        assertEquals("Should print Halfling", "Halfling", Al.getRace());
        assertEquals("Should print Halfling and Common", "Halfling and Common", Al.getLanguage());
        assertEquals("Should print 25", 25, Al.getSpeed());
    }

    /**
     * Test 3: Ensures that with an increase in 300 XP, the character levels up
     */
    @Test
    public void shouldLevelUp() {
        //Testing that making a character works
        Character Alex = new Character("Alex", "Human");
        assertEquals("Should print 0 as XP", 0, Alex.getXP() );
        assertEquals("Should print 1 as level", 1, Alex.getLevel() );
        //Testing that increasing XP works
        Alex.addXP(300);
        assertEquals("Should print 300 as XP", 300, Alex.getXP());
        assertEquals("Should print 2 as level", 2, Alex.getLevel());

    }

    /**
     * Test 4: Ensures that player starts with HP of 15.
     */
    @Test
    public void shouldHave15AsDefaultHP() {
        //Testing that making a character works
        Character Alex = new Character("Alex", "Human");
        assertEquals("Should print 15 as HP", 15, Alex.getHitPoints());
    }

    /**
     * Test 5: Ensures that with an increase in 300 XP, the character levels up and has an increase in HP
     */
    @Test
    public void shouldIncreaseHPWithLevelUp() {
        //Testing that making a character works
        Character Alex = new Character("Alex", "Human");
        assertEquals("Should print 0 as XP", 0, Alex.getXP() );
        assertEquals("Should print 15 as HP", 15, Alex.getHitPoints() );
        assertEquals("Should print 1 as level", 1, Alex.getLevel() );
        //Testing that increasing XP to next level causes increase in HP
        Alex.addXP(300);
        assertEquals("Should print 300 as XP", 300, Alex.getXP());
        assertEquals("Should print 2 as level", 2, Alex.getLevel());
        assertNotEquals("Should not be 15 as HP", 15, Alex.getHitPoints());
    }

}
