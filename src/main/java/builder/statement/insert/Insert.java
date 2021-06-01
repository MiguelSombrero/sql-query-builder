package builder.statement.insert;

import query.DMLQuery;
import builder.utils.StringAppender;

public class Insert extends Column {
    private StringAppender stringAppender;

    public Insert(DMLQuery query) {
        super(query);
        this.stringAppender = new StringAppender(query);
    }

    /**
     * Validates user input and appends
     * 'column(s)' into 'INSERT INTO (columns(s))' statement.
     *
     * @param listOfColumns List of column names to be appended.
     *
     * @return Columns class which can be used to append
     * VALUES or SELECT sub-query to query
     */
    public Column columns(String ...listOfColumns) {
        stringAppender.validateAndAppendList(listOfColumns);
        query.append(" ");
        return new Column(query);
    }
}
