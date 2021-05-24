package builder.statement.create.table.foreignkey;

import builder.Query;

public class OnAction extends ForeignKey {

    public OnAction(Query query) {
        super(query);
    }

    public Action onDelete() {
        query.append(" ON DELETE ");
        return new Action(query);
    }

    public Action onUpdate() {
        query.append(" ON UPDATE ");
        return new Action(query);
    }
}
