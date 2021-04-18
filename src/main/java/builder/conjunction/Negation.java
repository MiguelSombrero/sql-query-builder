package builder.conjunction;

public class Negation extends Condition {

    public Negation(StringBuilder builder) {
        super(builder);
    }

    public Condition not() {
        int indexOfLastBlank = this.builder.lastIndexOf(" ");
        insert(indexOfLastBlank, " NOT");
        return new Condition(this.builder);
    }

}
