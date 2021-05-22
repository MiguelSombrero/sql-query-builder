package builder.condition;

import builder.query.Query;
import builder.query.QueryAppender;
import builder.statement.select.SelectQueryBuilder;
import factory.ValidatorFactory;
import validation.Validator;

import java.util.Arrays;

public class SubQueryComparator {

    private Query query;

    public SubQueryComparator(Query query) {
        this.query = query;
    }

    public void equalsAny(SelectQueryBuilder query) {
        appendConditionWithSubQuery(" = ANY ", query);
    }

    public void equalsAll(SelectQueryBuilder query) {
        appendConditionWithSubQuery(" = ALL ", query);
    }

    public void greaterThanAny(SelectQueryBuilder query) {
        appendConditionWithSubQuery(" > ANY ", query);
    }

    public void greaterThanAll(SelectQueryBuilder query) {
        appendConditionWithSubQuery(" > ALL ", query);
    }

    public void greaterThanOrEqualAny(SelectQueryBuilder query) {
        appendConditionWithSubQuery(" >= ANY ", query);
    }

    public void greaterThanOrEqualAll(SelectQueryBuilder query) {
        appendConditionWithSubQuery(" >= ALL ", query);
    }

    public void lesserThanAny(SelectQueryBuilder query) {
        appendConditionWithSubQuery(" < ANY ", query);
    }

    public void lesserThanAll(SelectQueryBuilder query) {
        appendConditionWithSubQuery(" < ALL ", query);
    }

    public void lesserThanOrEqualAny(SelectQueryBuilder query) {
        appendConditionWithSubQuery(" <= ANY ", query);
    }

    public void lesserThanOrEqualAll(SelectQueryBuilder query) {
        appendConditionWithSubQuery(" <= ALL ", query);
    }

    public void isInSub(SelectQueryBuilder query) {
        appendConditionWithSubQuery(" IN ", query);
    }

    private void appendConditionWithSubQuery(String condition, SelectQueryBuilder subQuery) {
        query.append(condition);
        query.append("(");
        query.append(subQuery.build());
        query.append(")");
    }
}
