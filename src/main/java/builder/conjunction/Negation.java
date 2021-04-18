package builder.conjunction;

public class Negation extends Comparison {

    public Negation(StringBuilder builder) {
        super(builder);
    }

    public Comparison not() {
        int indexOfLastBlank = this.builder.lastIndexOf(" ");
        insert(indexOfLastBlank, " NOT");
        return new Comparison(this.builder);
    }

}
