package validation;

import org.junit.Test;
import utils.StringUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringValuePatternValidatorTest {

    private StringValuePatternValidator stringValuePatternValidator = new StringValuePatternValidator();
    
    @Test
    public void testDoesAcceptUnderScore() {
        assertTrue(stringValuePatternValidator.validate("person_id"));
    }

    @Test
    public void testDoesAcceptHyphen() {
        assertTrue(stringValuePatternValidator.validate("Miika-Lassi"));
    }

    @Test
    public void testDoesAcceptSpace() {
        assertTrue(stringValuePatternValidator.validate("Miika Lassi Kari"));
    }

    @Test
    public void testDoesAcceptCapitalized() {
        assertTrue(stringValuePatternValidator.validate("PERSON"));
    }

    @Test
    public void testMaximumValue() {
        assertTrue(stringValuePatternValidator.validate(StringUtils.repeatTextTimes("a", 255)));
    }

    @Test
    public void testDoesAcceptPercentInFront() {
        assertTrue(stringValuePatternValidator.validate("%erson_id"));
    }

    @Test
    public void testDoesAcceptPercentInEnd() {
        assertTrue(stringValuePatternValidator.validate("person_i%"));
    }

    @Test
    public void testDoesAcceptPercentInFrontAndEnd() {
        assertTrue(stringValuePatternValidator.validate("%erson_i%"));
    }

    @Test
    public void testDoesNotAcceptDot() {
        assertFalse(stringValuePatternValidator.validate("p.id"));
    }

    @Test
    public void testDoesNotAcceptDotInStart(){
        assertFalse(stringValuePatternValidator.validate(".id"));
    }

    @Test
    public void testDoesNotAcceptTwoDots() {
        assertFalse(stringValuePatternValidator.validate("p.id.id"));
    }

    @Test
    public void testDoesNotAcceptPercentInsideSentence() {
        assertFalse(stringValuePatternValidator.validate("pers%n_id"));
    }

    @Test
    public void testDoesNotAcceptTwoBlanksInARow() {
        assertFalse(stringValuePatternValidator.validate("Miika  Lassi"));
    }

    @Test
    public void testDoesNotAcceptSingleQuotes() {
        assertFalse(stringValuePatternValidator.validate("'Miika'"));
    }

    @Test
    public void testDoesNotAcceptSemicolon() {
        assertFalse(stringValuePatternValidator.validate(";DROP"));
    }

    @Test
    public void testDoesNotAcceptSQLComment() {
        assertFalse(stringValuePatternValidator.validate("--"));
    }

    @Test
    public void testDoesNotAcceptEmptyString() {
        assertFalse(stringValuePatternValidator.validate(""));
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(stringValuePatternValidator.validate(null));
    }

    @Test
    public void testDoesNotAcceptTooLongValue() {
        assertFalse(stringValuePatternValidator.validate(StringUtils.repeatTextTimes("a", 256)));
    }
}
