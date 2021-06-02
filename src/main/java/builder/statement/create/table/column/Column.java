package builder.statement.create.table.column;

import query.ddl.DDLQuery;
import builder.statement.create.table.foreignkey.ForeignKey;

public class Column extends ForeignKey {

    public Column(DDLQuery query) {
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
        stringAppender.validateAndAppend(column);
        return new ColumnType(query);
    }
}
