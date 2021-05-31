package builder.statement.create.table.column;

import query.SQLQuery;

public class ColumnType {

    private SQLQuery SQLQuery;

    public ColumnType(SQLQuery SQLQuery) {
        this.SQLQuery = SQLQuery;
    }

    /**
     * Appends 'datatype' into 'CREATE TABLE name (column datatype, ...)'
     * statement.
     *
     * @param dataType Datatype to be assigned to column
     *
     * @return Constrain class which can be used to
     * create constraints in selected column
     */
    public Constraint type(DataType dataType) {
        SQLQuery.append(" ");
        SQLQuery.append(dataType.getType());
        return new Constraint(SQLQuery);
    }
}
