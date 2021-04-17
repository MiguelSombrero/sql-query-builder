package builder;

import builder.field.FirstField;
import factory.QueryFactory;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class QueryFactoryTest {

    @Test
    public void testReturnSelect() {
        FirstField queryBuilder = QueryFactory.select();
        assertThat(queryBuilder, instanceOf(FirstField.class));
    }

}
