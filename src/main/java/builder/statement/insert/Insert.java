package builder.statement.insert;

public class Insert extends Column {

    public Insert(StringBuilder builder) {
        super(builder);
    }

    public Column columns(String ...listOfValues) {
        append("(");
        append(listOfValues[0]);

        for (int i = 1; i < listOfValues.length; i++) {
            append(", ");
            append(listOfValues[i]);
        }

        append(") ");
        return new Column(this.builder);
    }
}
