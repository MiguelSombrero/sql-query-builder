package validation;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DoubleValidatorTest {
    private static Logger logger = LoggerFactory.getLogger(DoubleValidatorTest.class);

    private DoubleValidator doubleValidator = new DoubleValidator();

    @Test
    public void testDoesAcceptZero() {
        assertTrue(doubleValidator.validate(0.0));
    }

    @Test
    public void testDoesAcceptValidPositiveValue() {
        assertTrue(doubleValidator.validate(123456789.11111111));
    }

    @Test
    public void testDoesAcceptValidNegativeValue() {
        assertTrue(doubleValidator.validate(-123456789.111111111));
    }

    //TODO test min and max
}
