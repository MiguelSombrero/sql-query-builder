package validation;

public class ByteArrayPatternValidator implements Validator<byte[]> {

    public boolean validate(byte[] input) {
        if (input == null) {
            return false;
        }
        if (input.getClass() != byte[].class) {
            return false;
        }
        if (input.length < 1) {
            return false;
        }
        return true;
    }
}
