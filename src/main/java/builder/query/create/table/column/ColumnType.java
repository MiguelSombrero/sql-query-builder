package builder.query.create.table.column;

import query.Clause;

public class ColumnType {

    private Clause clause;

    public ColumnType(Clause clause) {
        this.clause = clause;
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
        clause.append(" ");
        clause.append(dataType);
        return new Constraint(clause);
    }
}
