package bootcamp.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {
    // tests that a new object was created
    @Test
    public void testEmptyConstructor() {
        Message m = new Message();
        assertNotNull(m);
        assertNull(m.getMessage());
    }
}