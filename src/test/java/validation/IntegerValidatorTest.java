package validation;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntegerValidatorTest {
    private static Logger logger = LoggerFactory.getLogger(IntegerValidatorTest.class);

    private IntegerValidator integerValidator = new IntegerValidator();

    @Test
    public void testDoesAcceptZero() {
        assertTrue(integerValidator.validate(0));
    }

    @Test
    public void testDoesAcceptValidPositiveValue() {
        assertTrue(integerValidator.validate(Integer.MAX_VALUE - 1));
    }

    @Test
    public void testDoesAcceptValidNegativeValue() {
        assertTrue(integerValidator.validate(Integer.MIN_VALUE + 1));
    }

    @Test
    public void testDoesNotAcceptTooLargePositiveValue() {
        assertFalse(integerValidator.validate(Integer.MAX_VALUE));
    }

    @Test
    public void testDoesNotAcceptTooSmallValue() {
        assertFalse(integerValidator.validate(Integer.MIN_VALUE));
    }
}
