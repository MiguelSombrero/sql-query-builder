package builder.query.create.table.column;

import query.CreateQuery;

public class ColumnType {

    private CreateQuery query;

    public ColumnType(CreateQuery query) {
        this.query = query;
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
        query.append(" ");
        query.append(dataType);
        return new Constraint(query);
    }
}
