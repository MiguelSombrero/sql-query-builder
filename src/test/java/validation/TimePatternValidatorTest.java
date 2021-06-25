package validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TimePatternValidatorTest {

    private TimePatternValidator timePatternValidator = new TimePatternValidator();

    @Test
    public void testDoesAcceptTimeWithZeros() {
        assertTrue(timePatternValidator.validate("00:00:00"));
    }

    @Test
    public void testDoesAcceptTime() {
        assertTrue(timePatternValidator.validate("23:59:59"));
    }

    @Test
    public void testDoesNotAcceptTooLongTime() {
        assertFalse(timePatternValidator.validate("21:75:67"));
    }

    @Test
    public void testNotDoesAcceptDateWithTime() {
        assertFalse(timePatternValidator.validate("2021-05-15 21:00:00"));
    }

    @Test
    public void testDoesNotAcceptDate() {
        assertFalse(timePatternValidator.validate("2021-05-15"));
    }

    @Test
    public void testDoesNotAcceptSingleQuotes() {
        assertFalse(timePatternValidator.validate("'"));
    }

    @Test
    public void testDoesNotAcceptSemicolon() {
        assertFalse(timePatternValidator.validate(";"));
    }

    @Test
    public void testDoesNotAcceptSQLComment() {
        assertFalse(timePatternValidator.validate("--"));
    }

    @Test
    public void testDoesNotAcceptEmptyString() {
        assertFalse(timePatternValidator.validate(""));
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(timePatternValidator.validate(null));
    }
}
