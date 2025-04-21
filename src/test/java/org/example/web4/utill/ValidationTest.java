package org.example.web4.utill;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTest {

    @Test
    public void testValidationTrue() {
        assertTrue(Validation.validate(0, 0, 2));
    }
    @Test
    public void testValidationFalse() {
        assertFalse(Validation.validate(-6, 0, 2));
        assertFalse(Validation.validate(0, -4, 2));
        assertFalse(Validation.validate(0, 0, 4));
    }
    @Test
    public void testValidationEdge() {
        assertTrue(Validation.validate(-5, -3, 0));
        assertTrue(Validation.validate(3, 3, 3));
    }
}