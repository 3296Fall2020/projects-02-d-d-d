package org.openjfx;

import dnd.integration.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntegrationTest {

    SessionManager sessionManager;

    @Before
    public void setUp() {
        sessionManager = new SessionManager();
    }

    @Test
    public void shouldCreateUser() {
        assertTrue(sessionManager.createUser("bob", "sdfsdf"));
    }

    @Test
    public void shouldFetchToken() {
        assertTrue(sessionManager.authenticateSession("bob", "sdfsdf"));
    }



}
