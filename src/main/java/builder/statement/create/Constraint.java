package builder.statement.create;

public class Constraint extends Column {

    public Constraint(StringBuilder builder) {
        super(builder);
    }

    public Column notNull() {
        return insert(" NOT NULL");
    }

    public Column unique() {
        return insert(" UNIQUE");
    }

    public Column primaryKey() {
        return insert(" PRIMARY KEY");
    }

    private Column insert(String Column) {
        int index = lastIndexOfRightBracket();
        insert(index, Column);
        return new Column(this.builder);
    }
}
