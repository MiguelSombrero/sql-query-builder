package validation;

public class IntegerValidator implements Validator<Integer> {

    public boolean validate(Integer input) {
        if (input == null) {
            return false;
        }
        if (input >= Integer.MAX_VALUE) {
            return false;
        }
        if (input <= Integer.MIN_VALUE) {
            return false;
        }

        return true;
    }
}
