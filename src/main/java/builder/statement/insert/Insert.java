package builder.statement.insert;

import builder.Query;
import builder.utils.StringAppender;

public class Insert extends Column {
    private StringAppender stringAppender;

    public Insert(Query query) {
        super(query);
        this.stringAppender = new StringAppender(query);
    }

    public Column columns(String ...listOfColumns) {
        stringAppender.validateAndAppendList(listOfColumns);
        query.append(" ");
        return new Column(query);
    }
}
