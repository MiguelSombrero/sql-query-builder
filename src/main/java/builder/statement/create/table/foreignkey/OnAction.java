package builder.statement.create.table.foreignkey;

public class OnAction extends ForeignKey {

    public OnAction(StringBuilder queryString) {
        super(queryString);
    }

    public Action onDelete() {
        append(" ON DELETE ");
        return new Action(this.queryString);
    }

    public Action onUpdate() {
        append(" ON UPDATE ");
        return new Action(this.queryString);
    }
}
