package pt.isec.a2021144652.final_project.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConvertersTest {

    @Test
    public void fromList() {
        List<String> stringList = new ArrayList<>();
        stringList.add("test1");
        stringList.add("test2");
        stringList.add("test3");

        String result = Converters.fromList(stringList);

        assertEquals("test1,test2,test3,", result);
    }

    @Test
    public void toList() {
        String data = "test1,test2,test3,";

        List<String> result = Converters.toList(data);

        assertEquals("test1", result.get(0));
        assertEquals("test2", result.get(1));
        assertEquals("test3", result.get(2));
    }
}