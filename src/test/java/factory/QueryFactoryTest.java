package factory;

import builder.select.field.FirstField;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class QueryFactoryTest {

    @Test
    public void testReturnSelect() {
        FirstField queryBuilder = QueryFactory.select();
        assertThat(queryBuilder, IsInstanceOf.instanceOf(FirstField.class));
    }

    @Test
    public void testReturnSelectDistinct() {
        FirstField queryBuilder = QueryFactory.selectDistinct();
        assertThat(queryBuilder, IsInstanceOf.instanceOf(FirstField.class));
    }

}
