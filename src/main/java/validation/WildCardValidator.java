package validation;

public class WildCardValidator implements Validator {
    private String wildCard;

    public WildCardValidator(String wildCard) {
        this.wildCard = wildCard;
    }

    public boolean validate(String input) {
        if (input == null) {
            return false;
        }
        if (!input.equals(wildCard)) {
            return false;
        }

        return true;
    }
}
