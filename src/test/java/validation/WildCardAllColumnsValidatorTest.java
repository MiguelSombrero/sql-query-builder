package validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WildCardAllColumnsValidatorTest {

    private WildCardAllColumnsValidator validator = new WildCardAllColumnsValidator();

    @Test
    public void testDoesAcceptStar() {
        assertTrue(validator.validate("*"));
    }

    @Test
    public void testDoesNotAcceptTwoStars() {
        assertFalse(validator.validate("**"));
    }

    @Test
    public void testDoesNotAcceptAnyOtherASCIIThanStar() {
        for (int i=0; i < 255; i++) {
            String character = Character.toString((char) i);

            if (i == 42) {
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
