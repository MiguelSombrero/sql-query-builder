package validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateValidatorTest {

    private DateValidator dateValidator = new DateValidator();

    @Test
    public void testDoesAcceptDateWithTime() {
        assertTrue(dateValidator.validate("2021-05-15 21:00:00"));
    }

    @Test
    public void testDoesAcceptDateWithoutTime() {
        assertTrue(dateValidator.validate("2021-05-15"));
    }

    @Test
    public void testDoesNotAcceptSingleQuotes() {
        assertFalse(dateValidator.validate("'"));
    }

    @Test
    public void testDoesNotAcceptSemicolon() {
        assertFalse(dateValidator.validate(";"));
    }

    @Test
    public void testDoesNotAcceptSQLComment() {
        assertFalse(dateValidator.validate("--"));
    }

    @Test
    public void testDoesNotAcceptEmptyString() {
        assertFalse(dateValidator.validate(""));
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(dateValidator.validate(null));
    }
}
