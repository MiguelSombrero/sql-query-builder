package validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrValidatorTest {

    @Test
    public void testReturnsTrueWithValidInput() {
        OrValidator orValidator = new OrValidator(new StringPatternValidator());
        assertTrue(orValidator.validate("valid"));
    }

    @Test
    public void testReturnsTrueWithValidInputInOneValidator() {
        OrValidator orValidator = new OrValidator(
                new StringPatternValidator(), new DatePatternValidator());

        assertTrue(orValidator.validate("2020-02-02 21:00:00"));
    }

    @Test
    public void testReturnFalseWithBadInput() {
        OrValidator orValidator = new OrValidator(new StringPatternValidator());
        assertFalse(orValidator.validate("not-a-date"));
    }

    @Test
    public void testReturnFalseWithBadInputTwoValidators() {
        OrValidator orValidator = new OrValidator(
                new StringPatternValidator(), new DatePatternValidator());

        assertFalse(orValidator.validate(";DROP TABLE person"));
    }
}
