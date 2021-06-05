package utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void testReplaceQuestionMarksWithParams() {
        String query = "INSERT INTO person VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add("Miika");
        params.add("Somero");
        params.add("Kari");

        String replaced = StringUtils.replaceQuestionMarksWithParams(query, params);
        assertEquals("INSERT INTO person VALUES ('Miika', 'Somero', 'Kari')", replaced);
    }
}
