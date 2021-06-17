package validation;

public class DoubleValidator implements Validator<Double> {

    public boolean validate(Double input) {
        if (input == null) {
            return false;
        }
        if (input >= Double.MAX_VALUE) {
            return false;
        }
        if (input <= - Double.MAX_VALUE) {
            return false;
        }

        return true;
    }
}
