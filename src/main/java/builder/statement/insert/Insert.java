package builder.statement.insert;

import javax.xml.bind.ValidationException;

public class Insert extends Column {

    public Insert(StringBuilder queryString) {
        super(queryString);
    }

    public Column columns(String ...listOfColumns) throws ValidationException {
        validateAndAppendList(listOfColumns);
        append(" ");
        return new Column(this.queryString);
    }
}
