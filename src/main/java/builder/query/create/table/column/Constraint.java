package builder.query.create.table.column;

import query.CreateQuery;

public class Constraint extends Column {

    public Constraint(CreateQuery query) {
        super(query);
    }

    /**
     * Creates 'NOT NULL' constraint into
     * 'CREATE TABLE name (column1 datatype NOT NULL, ...)'
     * statement.
     *
     * @return Constrain class which can be used to
     * create constraints in selected column
     */
    public Constraint notNull() {
        return appendConstraint(" NOT NULL");
    }

    /**
     * Creates 'UNIQUE' constraint into
     * 'CREATE TABLE name (column1 datatype UNIQUE, ...)'
     * statement.
     *
     * @return Constrain class which can be used to
     * create constraints in selected column
     */
    public Constraint unique() {
        return appendConstraint(" UNIQUE");
    }

    /**
     * Creates 'PRIMARY KEY' constraint into
     * 'CREATE TABLE name (column1 datatype PRIMARY KEY, ...)'
     * statement.
     *
     * @return Constrain class which can be used to
     * create constraints in selected column
     */
    public Constraint primaryKey() {
        return appendConstraint(" PRIMARY KEY");
    }

    /**
     * Creates 'AUTO_INCREMENT' constraint into
     * 'CREATE TABLE name (column1 datatype AUTO_INCREMENT, ...)'
     * statement.
     *
     * @return Constrain class which can be used to
     * create constraints in selected column
     */
    public Constraint autoIncrement() {
        return appendConstraint(" AUTO_INCREMENT");
    }

    private Constraint appendConstraint(String column) {
        query.append(column);
        return this;
    }
}
