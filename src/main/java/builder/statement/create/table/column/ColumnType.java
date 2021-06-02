package builder.statement.create.table.column;

import query.ddl.DDLQuery;

public class ColumnType {

    private DDLQuery query;

    public ColumnType(DDLQuery query) {
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
