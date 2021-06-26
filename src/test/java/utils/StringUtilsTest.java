package utils;

import database.column.ColumnValue;
import database.column.StringColumnValue;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void testReplaceQuestionMarksWithInsertParams() {
        String query = "INSERT INTO person VALUES (?, ?, ?)";

        StringColumnValue param1 = new StringColumnValue("Miika");
        StringColumnValue param2 = new StringColumnValue("Somero");
        StringColumnValue param3 = new StringColumnValue("Kari");
        List<ColumnValue> params = new ArrayList<>();
        params.add(param1);
        params.add(param2);
        params.add(param3);

        String replaced = StringUtils.replaceQuestionMarksWithParams(query, params);
        assertEquals("INSERT INTO person VALUES ('Miika', 'Somero', 'Kari')", replaced);
    }

    @Test
    public void testReplaceQuestionMarksWithUpdateParams() {
        String query = "UPDATE person SET firstname = ?, lastname = ?";

        StringColumnValue param1 = new StringColumnValue("Miika");
        StringColumnValue param2 = new StringColumnValue("Somero");
        List<ColumnValue> params = new ArrayList<>();
        params.add(param1);
        params.add(param2);

        String replaced = StringUtils.replaceQuestionMarksWithParams(query, params);
        assertEquals("UPDATE person SET firstname = 'Miika', lastname = 'Somero'", replaced);
    }

    @Test
    public void repeatTextTimesWork() {
        String result = StringUtils.repeatTextTimes("a", 1000);
        assertEquals(1000, result.length());
    }

    @Test
    public void repeatTextTimesTextIsCorrect() {
        String result = StringUtils.repeatTextTimes("a", 10);
        assertEquals("aaaaaaaaaa", result);
    }
}
