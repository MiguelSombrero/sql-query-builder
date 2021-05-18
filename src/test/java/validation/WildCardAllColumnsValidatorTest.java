package validation;

import org.apache.commons.lang3.StringUtils;
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
    public void testDoesNotAcceptTwoStarsInASentence() {
        assertFalse(validator.validate("SELECT * FROM (SELECT * FROM PERSON)"));
    }

    @Test
    public void testDoesNotAcceptNull() {
        assertFalse(validator.validate(null));
    }

    @Test
    public void testDoesNotAcceptTooLongValue() {
        assertFalse(validator.validate(StringUtils.repeat("a", 256)));
    }
}
