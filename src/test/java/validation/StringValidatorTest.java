package validation;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringValidatorTest {

    private StringValidator stringValidator = new StringValidator();
    
    @Test
    public void testDoesAcceptUnderScore() {
        assertTrue(stringValidator.validate("person_id"));
    }

    @Test
    public void testDoesAcceptCapitalized() {
        assertTrue(stringValidator.validate("PERSON"));
    }

    @Test
    public void testDoesAcceptPercentAtStart() {
        assertTrue(stringValidator.validate("%rson_id"));
    }

    @Test
    public void testDoesAcceptPercentAtEnd() {
        assertTrue(stringValidator.validate("person_i%"));
    }

    @Test
    public void testDoesAcceptPercentAtStartAndEnd() {
        assertTrue(stringValidator.validate("%erson_i%"));
    }

    @Test
    public void testDoesAcceptOneDot() {
        assertTrue(stringValidator.validate("p.id"));
    }

    @Test
    public void testDoesNotAcceptDotInStart() {
        assertFalse(stringValidator.validate(".id"));
    }

    @Test
    public void testDoesNotAcceptTwoDots() {
        assertFalse(stringValidator.validate("p.id.id"));
    }

    @Test
    public void testDoesAcceptStar() {
        assertTrue(stringValidator.validate("*"));
    }

    @Test
    public void testMaximumValue() {
        assertTrue(stringValidator.validate(StringUtils.repeat("a", 255)));
    }

    @Test
    public void testDoesNotAcceptTwoStarsInARow() {
        assertFalse(stringValidator.validate("**"));
    }

    @Test
    public void testDoesNotAcceptTwoStarsInASentence() {
        assertFalse(stringValidator.validate("SELECT * FROM (SELECT * FROM PERSON)"));
    }

    @Test
    public void testDoesNotAcceptPercentInsideSentence() {
        assertFalse(stringValidator.validate("pers%n_id"));
    }

    @Test
    public void testDoesNotAcceptSingleQuotes() {
        assertFalse(stringValidator.validate("'Miika'"));
    }

    @Test
    public void testDoesNotAcceptSemicolon() {
        assertFalse(stringValidator.validate(";DROP"));
    }

    @Test
    public void testDoesNotAcceptSQLComment() {
        assertFalse(stringValidator.validate("--"));
    }

    @Test
    public void testDoesNotAcceptEmptyString() {
        assertFalse(stringValidator.validate(""));
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(stringValidator.validate(null));
    }

    @Test
    public void testDoesNotAcceptTooLongValue() {
        assertFalse(stringValidator.validate(StringUtils.repeat("a", 256)));
    }
}
