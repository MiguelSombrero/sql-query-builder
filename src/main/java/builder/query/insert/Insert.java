package builder.query.insert;

import builder.appender.StringAppender;
import query.Clause;

public class Insert extends Column {

    public Insert(Clause clause) {
        super(clause);
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
        StringAppender.validateAndAppendList(clause, listOfColumns);
        clause.append(" ");
        return new Column(clause);
    }
}
