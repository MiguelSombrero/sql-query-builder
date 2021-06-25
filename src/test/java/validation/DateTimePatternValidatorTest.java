package validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateTimePatternValidatorTest {

    private DateTimePatternValidator dateTimePatternValidator = new DateTimePatternValidator();

    @Test
    public void testDoesAcceptDateWithTime() {
        assertTrue(dateTimePatternValidator.validate("2021-05-15 21:00:00"));
    }

    @Test
    public void testDoesNotAcceptDateWithMilliseconds() {
        assertFalse(dateTimePatternValidator.validate("2021-05-15 21:00:00.5"));
    }

    @Test
    public void testDoesNotAcceptTooLongMonths() {
        assertFalse(dateTimePatternValidator.validate("2021-56-15 21:00:00"));
    }

    @Test
    public void testDoesNotAcceptTooLongDateWithTime() {
        assertFalse(dateTimePatternValidator.validate("2021-05-15 21:00:000"));
    }

    @Test
    public void testDoesNotAcceptDateWithoutTime() {
        assertFalse(dateTimePatternValidator.validate("2021-05-15"));
    }

    @Test
    public void testDoesNotAcceptTime() {
        assertFalse(dateTimePatternValidator.validate("21:00:00"));
    }

    @Test
    public void testDoesNotAcceptSingleQuotes() {
        assertFalse(dateTimePatternValidator.validate("'"));
    }

    @Test
    public void testDoesNotAcceptSemicolon() {
        assertFalse(dateTimePatternValidator.validate(";"));
    }

    @Test
    public void testDoesNotAcceptSQLComment() {
        assertFalse(dateTimePatternValidator.validate("--"));
    }

    @Test
    public void testDoesNotAcceptEmptyString() {
        assertFalse(dateTimePatternValidator.validate(""));
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(dateTimePatternValidator.validate(null));
    }
}
