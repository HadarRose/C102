package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatusListTest {
    // tests that a new object was created
    @Test
    public void testEmptyConstructor() {
        StatusList list = new StatusList();
        assertNotNull(list);
        assertNull(list.getStatusList());
    }
}