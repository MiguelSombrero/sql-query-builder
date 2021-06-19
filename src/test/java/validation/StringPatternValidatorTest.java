package validation;

import org.junit.Test;
import utils.StringUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringPatternValidatorTest {

    private StringPatternValidator stringPatternValidator = new StringPatternValidator();
    
    @Test
    public void testDoesAcceptUnderScore() {
        assertTrue(stringPatternValidator.validate("person_id"));
    }

    @Test
    public void testDoesAcceptCapitalized() {
        assertTrue(stringPatternValidator.validate("PERSON"));
    }

    @Test
    public void testDoesAcceptOneDot() {
        assertTrue(stringPatternValidator.validate("p.id"));
    }

    @Test
    public void testMaximumValue() {
        assertTrue(stringPatternValidator.validate(StringUtils.repeatTextTimes("a", 255)));
    }

    @Test
    public void testDoesNotAcceptDotInStart(){
        assertFalse(stringPatternValidator.validate(".id"));
    }

    @Test
    public void testDoesNotAcceptTwoDots() {
        assertFalse(stringPatternValidator.validate("p.id.id"));
    }

    @Test
    public void testDoesNotAcceptPercentInsideSentence() {
        assertFalse(stringPatternValidator.validate("pers%n_id"));
    }

    @Test
    public void testDoesNotAcceptSingleQuotes() {
        assertFalse(stringPatternValidator.validate("'Miika'"));
    }

    @Test
    public void testDoesNotAcceptSemicolon() {
        assertFalse(stringPatternValidator.validate(";DROP"));
    }

    @Test
    public void testDoesNotAcceptSQLComment() {
        assertFalse(stringPatternValidator.validate("--"));
    }

    @Test
    public void testDoesNotAcceptEmptyString() {
        assertFalse(stringPatternValidator.validate(""));
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(stringPatternValidator.validate(null));
    }

    @Test
    public void testDoesNotAcceptTooLongValue() {
        assertFalse(stringPatternValidator.validate(StringUtils.repeatTextTimes("a", 256)));
    }
}
