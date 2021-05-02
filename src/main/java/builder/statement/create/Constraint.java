package builder.statement.create;

public class Constraint extends Column {

    public Constraint(StringBuilder builder) {
        super(builder);
    }

    public Constraint notNull() {
        return insert(" NOT NULL");
    }

    public Constraint unique() {
        return insert(" UNIQUE");
    }

    public Constraint primaryKey() {
        return insert(" PRIMARY KEY");
    }

    private Constraint insert(String constraint) {
        int index = lastIndexOfRightBracket();
        insert(index, constraint);
        return new Constraint(this.builder);
    }
}
