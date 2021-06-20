package builder.query.create.table.column;

import builder.appender.StringAppender;
import builder.query.create.table.foreignkey.ForeignKey;
import query.CreateQuery;

public class Column extends ForeignKey {

    public Column(CreateQuery query) {
        super(query);
    }

    /**
     * Validates user input and appends ', column' into
     * 'CREATE TABLE name (column1 datatype, column2 datatype, ...)'
     * statement.
     *
     * @param column name to be created in table
     *
     * @return ColumnType class which can be used to
     * append column type to selected column
     */
    public ColumnType column(String column) {
        query.append(", ");
        StringAppender.validateAndAppend(query, column);
        return new ColumnType(query);
    }
}
