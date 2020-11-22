package org.openjfx;


import dnd.characters.*;
import dnd.characters.Character;
import dnd.weapons.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

//Contains all of the tests to ensure that character generation of various races works
public class CharacterTest {
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
     * Test 1: Ensures that Character can be generated with name and abilities
     */
    @Test
    public void shouldCreateCharacter() {
        //Testing that making a character works
        Character Alex = new Character("Alex");
        //want to make sure that random numbers are being produced
        System.out.println("The character's strength is " + Alex.getStrength());
        System.out.println("The character's dexterity is " + Alex.getDexterity());
        System.out.println("The character's constitution is " + Alex.getConstitution());
        System.out.println("The character's intelligence is " + Alex.getIntelligence());
        System.out.println("The character's wisdom is " + Alex.getWisdom());
        System.out.println("The character's charisma is " + Alex.getCharisma());
        System.out.println("The character's alignment is " + Alex.getAlignment());
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
        Halfling Al = new Halfling("Al");
        assertEquals("Should print Al", "Al", Al.getName());
        assertEquals("Should print Halfling", "Halfling", Al.getRace());
        assertEquals("Should print Halfling and Common", "Halfling and Common", Al.getLanguage());
        assertEquals("Should print 25", 25, Al.getSpeed());
        assertEquals("Should be Lawful Good", "Lawful Good", Al.getAlignment());
    }

    /**
     * Test 3: Ensures that with an increase in 300 XP, the character levels up
     */
    @Test
    public void shouldLevelUp() {
        //Testing that making a character works
        Character Alex = new Character("Alex");
        assertEquals("Should print 0 as XP", 0, Alex.getXP() );
        assertEquals("Should print 1 as level", 1, Alex.getLevel() );
        //Testing that increasing XP works
        Alex.addXP(300);
        assertEquals("Should print 300 as XP", 300, Alex.getXP());
        assertEquals("Should print 2 as level", 2, Alex.getLevel());

    }

    /**
     * Test 4: Ensures that an elf can be created
     */
    @Test
    public void shouldCreateElf() {
        //Testing that other race characters can be made.
        Elf Al = new Elf("Al");
        assertEquals("Should print Al", "Al", Al.getName());
        assertEquals("Should print Elf", "Elf", Al.getRace());
        assertEquals("Should print Elvish and Common", "Elvish and Common", Al.getLanguage());
        assertEquals("Should print 30", 30, Al.getSpeed());
        assertEquals("Should be Chaotic Good", "Chaotic Good", Al.getAlignment());
    }

    /**
     * Test 5: Ensures that a dwarf can be created
     */
    @Test
    public void shouldCreateDwarf() {
        //Testing that other race characters can be made.
        Dwarf Al = new Dwarf("Al");
        assertEquals("Should print Al", "Al", Al.getName());
        assertEquals("Should print Dwarf", "Dwarf", Al.getRace());
        assertEquals("Should print Dwarfish and Common", "Dwarfish and Common", Al.getLanguage());
        assertEquals("Should print 25", 25, Al.getSpeed());
        assertEquals("Should be Lawful Good", "Lawful Good", Al.getAlignment());
    }

    /**
     * Test 6: Ensures that different class memberships can be made
     */
    @Test
    public void shouldMakeDifferentClasses() {
        //Testing that other race characters can be made.
        Dwarf Al = new Dwarf("Al");
        Al.setClassMembership("Wizard");
        assertEquals("Should print Wizard", "Wizard", Al.getClassMembership());
        ObjectEquals("Should print wand", new Wand(), Al.getWeapon());
        Al.setClassMembership("Cleric");
        assertEquals("Should print Cleric", "Cleric", Al.getClassMembership());
        ObjectEquals("Should print Staff", new Staff(), Al.getWeapon());
        Al.setClassMembership("Rogue");
        assertEquals("Should print Rogue", "Rogue", Al.getClassMembership());
        ObjectEquals("Should print Bow", new Bow(), Al.getWeapon());
        Al.setClassMembership("Fighter");
        ObjectEquals("Should print Sword", new Sword(), Al.getWeapon());
        assertEquals("Should print Fighter", "Fighter", Al.getClassMembership());


    }

    /**
     * Test 7: Ensures that the factory class is working properly
     */
    @Test
    public void factoryShouldCreateInstance() {
        Character res= CharacterFactory.createCharacter("Dwarf", "bob");
        assertTrue(res instanceof Dwarf);
    }

}
