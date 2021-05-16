package validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateValidatorTest {

    @Test
    public void testDoesAcceptDateWithTime() {
        assertTrue(DateValidator.validate("2021-05-15 21:00:00"));
    }

    @Test
    public void testDoesAcceptDateWithoutTime() {
        assertTrue(DateValidator.validate("2021-05-15"));
    }

    @Test
    public void testDoesNotAcceptSingleQuotes() {
        assertFalse(DateValidator.validate("'"));
    }

    @Test
    public void testDoesNotAcceptSemicolon() {
        assertFalse(DateValidator.validate(";"));
    }

    @Test
    public void testDoesNotAcceptSQLComment() {
        assertFalse(DateValidator.validate("--"));
    }

    @Test
    public void testDoesNotAcceptEmptyString() {
        assertFalse(DateValidator.validate(""));
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(DateValidator.validate(null));
    }
}
