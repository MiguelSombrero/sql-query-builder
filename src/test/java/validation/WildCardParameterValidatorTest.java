package validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WildCardParameterValidatorTest {

    private WildCardParameterValidator validator = new WildCardParameterValidator();

    @Test
    public void testDoesAcceptQuestionMark() {
        assertTrue(validator.validate("?"));
    }

    @Test
    public void testDoesNotAcceptTwoQuestionMarks() {
        assertFalse(validator.validate("??"));
    }

    @Test
    public void testDoesNotAcceptAnyOtherASCIIThanQuestionMark() {
        for (int i=0; i < 255; i++) {
            String character = Character.toString((char) i);

            if (i == 63) {
                assertTrue(validator.validate(character));
            } else {
                assertFalse(validator.validate(character));
            }
        }
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(validator.validate(null));
    }

}
