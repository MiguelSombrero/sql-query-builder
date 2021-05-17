package builder.statement.insert;

public class Insert extends Column {

    public Insert(StringBuilder queryString) {
        super(queryString);
    }

    public Column columns(String ...listOfColumns) {
        validateAndAppendList(listOfColumns);
        append(" ");
        return new Column(this.queryString);
    }
}
