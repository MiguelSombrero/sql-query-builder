package factory;

import builder.field.FirstField;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;

public class QueryFactoryTest {

    @Test
    public void testReturnSelect() {
        FirstField queryBuilder = QueryFactory.select();
        Assert.assertThat(queryBuilder, IsInstanceOf.instanceOf(FirstField.class));
    }

}
