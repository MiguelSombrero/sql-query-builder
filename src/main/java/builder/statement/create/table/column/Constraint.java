package builder.statement.create.table.column;

public class Constraint extends Column {

    public Constraint(StringBuilder builder) {
        super(builder);
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
        append(column);
        return this;
    }
}
