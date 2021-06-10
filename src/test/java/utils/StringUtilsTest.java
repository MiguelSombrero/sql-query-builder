package utils;

import database.column.AbstractColumnValue;
import database.column.StringColumnValue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void testReplaceQuestionMarksWithParams() {
        String query = "INSERT INTO person VALUES (?, ?, ?)";

        StringColumnValue param1 = new StringColumnValue("Miika");
        StringColumnValue param2 = new StringColumnValue("Somero");
        StringColumnValue param3 = new StringColumnValue("Kari");
        List<AbstractColumnValue> params = new ArrayList<>();
        params.add(param1);
        params.add(param2);
        params.add(param3);

        String replaced = StringUtils.replaceQuestionMarksWithParams(query, params);
        assertEquals("INSERT INTO person VALUES ('Miika', 'Somero', 'Kari')", replaced);
    }
}
