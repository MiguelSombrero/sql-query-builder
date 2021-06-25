package validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimestampPatternValidatorTest {

    private TimestampPatternValidator timeStampPatternValidator = new TimestampPatternValidator();

    @Test
    public void testDoesAcceptDateTimeWithMilliseconds() {
        assertTrue(timeStampPatternValidator.validate("2021-05-15 21:00:00.123"));
    }

    @Test
    public void testDoesNotAcceptTooLongMonths() {
        assertFalse(timeStampPatternValidator.validate("2021-56-15 21:00:00.000"));
    }

    @Test
    public void testDoesNotAcceptTooLongDateWithTime() {
        assertFalse(timeStampPatternValidator.validate("2021-05-15 21:00:000.000"));
    }

    @Test
    public void testDoesNotAcceptDateWithoutTime() {
        assertFalse(timeStampPatternValidator.validate("2021-05-15"));
    }

    @Test
    public void testDoesNotAcceptSingleQuotes() {
        assertFalse(timeStampPatternValidator.validate("'"));
    }

    @Test
    public void testDoesNotAcceptSemicolon() {
        assertFalse(timeStampPatternValidator.validate(";"));
    }

    @Test
    public void testDoesNotAcceptSQLComment() {
        assertFalse(timeStampPatternValidator.validate("--"));
    }

    @Test
    public void testDoesNotAcceptEmptyString() {
        assertFalse(timeStampPatternValidator.validate(""));
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(timeStampPatternValidator.validate(null));
    }
}
