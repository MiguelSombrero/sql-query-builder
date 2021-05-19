package validation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WildCardValidatorTest {

    private WildCardValidator validator;

    @Before
    public void setUp() {
        this.validator = new WildCardValidator("*");
    }

    @Test
    public void testDoesAcceptWildCard() {
        assertTrue(validator.validate("*"));
    }

    @Test
    public void testDoesNotAcceptTwoStars() {
        assertFalse(validator.validate("**"));
    }

    @Test
    public void testDoesNotAcceptAnyOtherASCIIThanWildCard() {
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
