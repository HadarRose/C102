package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorMessageTest {
    // tests that a new object was created
    @Test
    public void testEmptyConstructor(){
        ErrorMessage e = new ErrorMessage();
        assertNotNull(e);
        assertNull(e.getMessage());
        assertEquals(0, e.getStatusCode());
    }
}