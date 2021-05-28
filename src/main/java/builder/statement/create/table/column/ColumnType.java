package builder.statement.create.table.column;

import builder.Query;

public class ColumnType {

    private Query query;

    public ColumnType(Query query) {
        this.query = query;
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
        query.append(" ");
        query.append(dataType.getType());
        return new Constraint(query);
    }
}
