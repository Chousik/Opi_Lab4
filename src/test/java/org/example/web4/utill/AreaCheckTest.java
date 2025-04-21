package org.example.web4.utill;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AreaCheckTest {

    @Test
    public void testAreaCheckSquare() {
        assertTrue(AreaCheck.isInArea(0, 0, 0));
        assertTrue(AreaCheck.isInArea(-1, 0, 2));
    }
    @Test
    public void testAreaCheckOutside() {
        assertFalse(AreaCheck.isInArea(1, -2, 1));
        assertFalse(AreaCheck.isInArea(-2, 2, 2));
        assertFalse(AreaCheck.isInArea(2, -1, 2));
        assertFalse(AreaCheck.isInArea(3, 3, 2));
    }
    @Test
    public void testAreaCheckCircle() {
        assertTrue(AreaCheck.isInArea(1, 1, 2));
    }
    @Test
    public void testAreaCheckTriangle() {
        assertTrue(AreaCheck.isInArea(-1, 1, 2));

    }
}
