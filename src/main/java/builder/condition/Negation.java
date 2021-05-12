package builder.condition;

public class Negation extends Comparison {

    public Negation(StringBuilder queryString) {
        super(queryString);
    }

    public Comparison not() {
        this.queryString.insert(0, "NOT ");
        return new Comparison(this.queryString);
    }

}
