package builder.clause.where;

public class Negation extends Comparison {

    public Negation(StringBuilder builder) {
        super(builder);
    }

    public Comparison not() {
        insert(0, "NOT ");
        return new Comparison(this.builder);
    }

}
