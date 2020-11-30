package org.openjfx;

import dnd.characters.Character;
import dnd.characters.Dwarf;
import dnd.characters.Elf;
import dnd.integration.*;
import dnd.weapons.Sword;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class IntegrationTest {

    SessionManager sessionManager;

    @Before
    public void setUp() {
        sessionManager = SessionManager.getInstance();
        sessionManager.createUser("bobthebuilder13243", "bobspass");
        sessionManager.authenticateSession("bobthebuilder13243", "bobspass");

        List<Character> noChars = new ArrayList<>();
        sessionManager.saveGame("nocharsgame", noChars);
        sessionManager.saveGame("secondnocharsgame", noChars);
    }

    @Test
    public void shouldCreateUserAndGetToken() {
        assertTrue(sessionManager.createUser("bob", "sdfsdf"));
        assertTrue(sessionManager.authenticateSession("bob", "sdfsdf"));
    }

    @Test
    public void shouldSaveThenGetGame() {
        Character dwarf = new Dwarf("steve");
        dwarf.setLevel(4);
        dwarf.setWeapon(new Sword());
        Character elf = new Elf("joe");
        List<Character> toSave = new ArrayList<>();
        toSave.add(dwarf);
        toSave.add(elf);
        sessionManager.saveGame("testgame", toSave);
        List<Character> chars = sessionManager.fetchCharactersForGame("testgame");
        assertEquals(chars.get(0), dwarf);
        assertEquals(chars.get(1), elf);
    }

    @Test
    public void shouldGetAllGames() {
        List<String> games = sessionManager.fetchGamesForPlayer();
        assertEquals(games.get(0), "nocharsgame");
    }



}
