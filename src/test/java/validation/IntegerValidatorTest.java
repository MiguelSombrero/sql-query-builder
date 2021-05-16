package validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntegerValidatorTest {

    @Test
    public void testDoesAcceptZero() {
        assertTrue(IntegerValidator.validate(0));
    }

    @Test
    public void testDoesAcceptNegative() {
        assertTrue(IntegerValidator.validate(-123));
    }

    @Test
    public void testDoesAcceptPositive() {
        assertTrue(IntegerValidator.validate(1232235456));
    }

    @Test
    public void testDoesNotAcceptTooLargeValue() {
        assertFalse(IntegerValidator.validate(Integer.MAX_VALUE));
    }

    @Test
    public void testDoesNotAcceptTooSmallValue() {
        assertFalse(IntegerValidator.validate(Integer.MIN_VALUE));
    }
}
