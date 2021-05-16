package validation;

import org.junit.Test;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.assertTrue;

public class InputValidatorTest {

    @Test
    public void testReturnsTrueWithValidInput() throws ValidationException {
        InputValidator inputValidator = new InputValidator(new StringValidator());
        assertTrue(inputValidator.validOrThrow("valid"));
    }

    @Test
    public void testReturnsTrueWithValidInputInOneValidator() throws ValidationException {
        InputValidator inputValidator = new InputValidator(
                new StringValidator(), new DateValidator());

        assertTrue(inputValidator.validOrThrow("2020-02-02 21:00:00"));
    }

    @Test(expected = ValidationException.class)
    public void testThrowsExceptionWithBadInput() throws ValidationException {
        InputValidator inputValidator = new InputValidator(new StringValidator());
        inputValidator.validOrThrow("not-a-date");
    }

    @Test(expected = ValidationException.class)
    public void testThrowsExceptionWithBadInputTwoValidators() throws ValidationException {
        InputValidator inputValidator = new InputValidator(
                new StringValidator(), new DateValidator());

        inputValidator.validOrThrow(";DROP TABLE person");
    }
}
