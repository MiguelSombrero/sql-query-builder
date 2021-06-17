package validation;

public interface Validator<T> {
    boolean validate(T input);
}
