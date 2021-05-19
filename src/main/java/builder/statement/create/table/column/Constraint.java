package builder.statement.create.table.column;

import builder.query.Query;

public class Constraint extends Column {

    public Constraint(Query query) {
        super(query);
    }

    public Constraint notNull() {
        return appendConstraint(" NOT NULL");
    }

    public Constraint unique() {
        return appendConstraint(" UNIQUE");
    }

    public Constraint primaryKey() {
        return appendConstraint(" PRIMARY KEY");
    }

    public Constraint autoIncrement() {
        return appendConstraint(" AUTO_INCREMENT");
    }

    private Constraint appendConstraint(String column) {
        query.append(column);
        return this;
    }
}
