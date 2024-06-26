package pt.isec.a2021144652.final_project.utils;

import androidx.room.TypeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converters {
    @TypeConverter
    public static String fromList(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : stringList) {
            stringBuilder.append(s);
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    @TypeConverter
    public static List<String> toList(String data) {
        if (data == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(data.split(",")));
    }
}
