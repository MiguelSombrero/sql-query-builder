package builder.statement.create.table.foreignkey;

public class OnAction extends ForeignKey {

    public OnAction(StringBuilder builder) {
        super(builder);
    }

    public Action onDelete() {
        append(" ON DELETE ");
        return new Action(this.builder);
    }

    public Action onUpdate() {
        append(" ON UPDATE ");
        return new Action(this.builder);
    }
}
