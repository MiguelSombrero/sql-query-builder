package validation;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringValidatorTest {

    @Test
    public void testDoesAcceptUnderScore() {
        assertTrue(StringValidator.validate("person_id"));
    }

    @Test
    public void testDoesAcceptStar() {
        assertTrue(StringValidator.validate("*"));
    }

    @Test
    public void testDoesAcceptQuestionMark() {
        assertTrue(StringValidator.validate("?"));
    }

    @Test
    public void testMaximumValue() {
        assertTrue(StringValidator.validate(StringUtils.repeat("a", 65534)));
    }

    @Test
    public void testDoesNotAcceptTwoStarsInARow() {
        assertFalse(StringValidator.validate("**"));
    }

    @Test
    public void testDoesNotAcceptTwoStarsInASentence() {
        assertFalse(StringValidator.validate("SELECT * FROM (SELECT * FROM PERSON)"));
    }

    @Test
    public void testDoesNotAcceptTwoQuestionMarksInARow() {
        assertFalse(StringValidator.validate("??"));
    }

    @Test
    public void testDoesNotAcceptStartAndQuestionMark() {
        assertFalse(StringValidator.validate("*?"));
    }

    @Test
    public void testDoesNotAcceptTwoQuestionMarksInASentence() {
        assertFalse(StringValidator.validate("SELECT name FROM person WHERE age = ? AND birthdate = ?"));
    }

    @Test
    public void testDoesNotAcceptSingleQuotes() {
        assertFalse(StringValidator.validate("'Miika'"));
    }

    @Test
    public void testDoesNotAcceptSemicolon() {
        assertFalse(StringValidator.validate(";DROP"));
    }

    @Test
    public void testDoesNotAcceptSQLComment() {
        assertFalse(StringValidator.validate("--"));
    }

    @Test
    public void testDoesNotAcceptEmptyString() {
        assertFalse(StringValidator.validate(""));
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(StringValidator.validate(null));
    }

    @Test
    public void testDoesNotAcceptTooLongValue() {
        assertFalse(StringValidator.validate(StringUtils.repeat("a", 65535)));
    }
}
