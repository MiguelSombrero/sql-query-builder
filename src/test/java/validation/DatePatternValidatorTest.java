package validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DatePatternValidatorTest {

    private DatePatternValidator datePatternValidator = new DatePatternValidator();

    @Test
    public void testDoesAcceptDateWithoutTime() {
        assertTrue(datePatternValidator.validate("2021-05-15"));
    }


    @Test
    public void testDoesNotAcceptDateWithTime() {
        assertFalse(datePatternValidator.validate("2021-05-15 21:00:00"));
    }

    @Test
    public void testDoesNotAcceptTooLongDateWithTime() {
        assertFalse(datePatternValidator.validate("2021-05-15 21:00:000"));
    }

    @Test
    public void testDoesNotAcceptSingleQuotes() {
        assertFalse(datePatternValidator.validate("'"));
    }

    @Test
    public void testDoesNotAcceptSemicolon() {
        assertFalse(datePatternValidator.validate(";"));
    }

    @Test
    public void testDoesNotAcceptSQLComment() {
        assertFalse(datePatternValidator.validate("--"));
    }

    @Test
    public void testDoesNotAcceptEmptyString() {
        assertFalse(datePatternValidator.validate(""));
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(datePatternValidator.validate(null));
    }
}
