package builder.query.create.table.column;

import query.Statement;

public class ColumnType {

    private Statement statement;

    public ColumnType(Statement statement) {
        this.statement = statement;
    }

    /**
     * Appends 'datatype' into 'CREATE TABLE name (column datatype, ...)'
     * statement.
     *
     * @param dataType Datatype as a String to be assigned to column.
     * For example VARCHAR or DECIMAL(5,2)
     *
     * @return Constrain class which can be used to
     * create constraints in selected column
     */
    public Constraint type(String dataType) {
        statement.append(" ");
        statement.append(dataType);
        return new Constraint(statement);
    }
}
