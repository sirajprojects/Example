package batch.org.springweb.configuration;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

public class example {

    // Utility method to adjust Timestamp based on TimeZone
    public static void adjustTimestampByTimeZone(Map<String, Object> map, String key, TimeZone timeZone) {
        if (map.containsKey(key)) {
            Object value = map.get(key);
            if (value instanceof Timestamp) {
                Timestamp timestamp = (Timestamp) value;

                // Convert Timestamp to Date
                Date date = new Date(timestamp.getTime());

                // Get the timezone offset
                Calendar calendar = Calendar.getInstance(timeZone);
                int offset = calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET);

                // Adjust the time
                Date adjustedDate = new Date(date.getTime() + offset);

                // Convert back to Timestamp
                Timestamp adjustedTimestamp = new Timestamp(adjustedDate.getTime());

                // Put the adjusted Timestamp into the map
                map.put(key, adjustedTimestamp);
            }
        }
    }

    // Utility method to convert BigDecimal to Integer
    public static void convertBigDecimalToInteger(Map<String, Object> map, String key) {
        if (map.containsKey(key)) {
            Object value = map.get(key);
            if (value instanceof BigDecimal) {
                BigDecimal bigDecimalValue = (BigDecimal) value;
                Integer intValue = bigDecimalValue.intValue();
                map.put(key, intValue);
            }
        }
    }

    // Utility method to convert BigDecimal to String
    public static void convertBigDecimalToString(Map<String, Object> map, String key) {
        if (map.containsKey(key)) {
            Object value = map.get(key);
            if (value instanceof BigDecimal) {
                BigDecimal bigDecimalValue = (BigDecimal) value;
                String stringValue = bigDecimalValue.toString();
                map.put(key, stringValue);
            }
        }
    }
}
