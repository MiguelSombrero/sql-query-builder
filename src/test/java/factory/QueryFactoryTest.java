package factory;

import builder.statement.select.column.FirstColumn;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class QueryFactoryTest {

    @Test
    public void testReturnSelect() {
        FirstColumn queryBuilder = QueryFactory.select();
        assertThat(queryBuilder, IsInstanceOf.instanceOf(FirstColumn.class));
    }

    @Test
    public void testReturnSelectDistinct() {
        FirstColumn queryBuilder = QueryFactory.selectDistinct();
        assertThat(queryBuilder, IsInstanceOf.instanceOf(FirstColumn.class));
    }

}
