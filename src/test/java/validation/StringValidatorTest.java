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
    public void testDoesAcceptCapitalized() {
        assertTrue(StringValidator.validate("PERSON"));
    }

    @Test
    public void testDoesAcceptPercentAtStart() {
        assertTrue(StringValidator.validate("%rson_id"));
    }

    @Test
    public void testDoesAcceptPercentAtEnd() {
        assertTrue(StringValidator.validate("person_i%"));
    }

    @Test
    public void testDoesAcceptPercentAtStartAndEnd() {
        assertTrue(StringValidator.validate("%erson_i%"));
    }

    @Test
    public void testDoesAcceptOneDot() {
        assertTrue(StringValidator.validate("p.id"));
    }

    @Test
    public void testDoesNotAcceptDotInStart() {
        assertFalse(StringValidator.validate(".id"));
    }

    @Test
    public void testDoesNotAcceptTwoDots() {
        assertFalse(StringValidator.validate("p.id.id"));
    }

    @Test
    public void testDoesAcceptStar() {
        assertTrue(StringValidator.validate("*"));
    }

    @Test
    public void testMaximumValue() {
        assertTrue(StringValidator.validate(StringUtils.repeat("a", 255)));
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
    public void testDoesNotAcceptPercentInsideSentence() {
        assertFalse(StringValidator.validate("pers%n_id"));
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
        assertFalse(StringValidator.validate(StringUtils.repeat("a", 256)));
    }
}
