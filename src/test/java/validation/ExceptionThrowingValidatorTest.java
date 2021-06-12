package validation;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ExceptionThrowingValidatorTest {

    private Validator<String> dateValidator = new ExceptionThrowingValidator<>(new DatePatternValidator());
    private Validator<String> stringValidator = new ExceptionThrowingValidator<>(new StringPatternValidator());

    @Test
    public void testDoesAcceptDateWithoutTime() {
        assertTrue(dateValidator.validate("2021-05-15"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptDateWithTime() {
        assertTrue(dateValidator.validate("2021-05-15 21:00:00"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptSingleQuotes() {
        dateValidator.validate("'");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptSemicolon() {
        dateValidator.validate(";");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptSQLComment() {
        dateValidator.validate("--");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptEmptyString() {
        dateValidator.validate("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptNull() {
        dateValidator.validate(null);
    }

    @Test
    public void testDoesAcceptUnderScore() {
        assertTrue(stringValidator.validate("person_id"));
    }

    @Test
    public void testDoesAcceptCapitalized() {
        assertTrue(stringValidator.validate("PERSON"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptDotInStart(){
        stringValidator.validate(".id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptTwoDots() {
        stringValidator.validate("p.id.id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptTwoStarsInARow() {
        stringValidator.validate("**");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptTwoStarsInASentence() {
        stringValidator.validate("SELECT * FROM (SELECT * FROM PERSON)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptPercentInsideSentence() {
        stringValidator.validate("pers%n_id");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoesNotAcceptTooLongValue() {
        stringValidator.validate(StringUtils.repeat("a", 256));
    }
}
