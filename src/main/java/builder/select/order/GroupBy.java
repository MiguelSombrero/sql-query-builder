package builder.select.order;

public class GroupBy extends Orderer {

    public GroupBy(StringBuilder builder) {
        super(builder);
    }

    public GroupBy column(String columnName) {
        append(", ");
        append(columnName);
        return this;
    }

    public FirstOrderBy orderBy() {
        append(" ORDER BY ");
        return new FirstOrderBy(this.builder);
    }
}
