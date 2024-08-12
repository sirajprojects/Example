import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Service // or @Component if you prefer
public class MyServiceClass {

    // Autowire the MyBatis mapper
    @Autowired
    private YourMapper yourMapper;

    // The get method takes a String parameter 'rel'
    public List<Map<String, Object>> get(String rel) {

        // Call the mapper method and retrieve the result
        List<Map<String, Object>> result = yourMapper.yourSelectQuery(rel);

        // Define the Central Time Zone
        TimeZone centralTimeZone = TimeZone.getTimeZone("CST");

        // Process the result
        for (Map<String, Object> map : result) {
            // Handle specific column conversions
            convertTimestampToCalendar(map, "bb", centralTimeZone);
            convertBigDecimalToInteger(map, "b1");
            convertBigDecimalToString(map, "b2");

            // Add more conversions as needed
        }

        // Return the modified result
        return result;
    }

    // Utility method to convert Timestamp to Calendar
    private void convertTimestampToCalendar(Map<String, Object> map, String key, TimeZone timeZone) {
        if (map.containsKey(key)) {
            Object value = map.get(key);
            if (value instanceof Timestamp) {
                Timestamp timestamp = (Timestamp) value;
                Calendar calendar = Calendar.getInstance(timeZone);
                calendar.setTime(timestamp);
                map.put(key, calendar);
            }
        }
    }

    // Utility method to convert BigDecimal to Integer
    private void convertBigDecimalToInteger(Map<String, Object> map, String key) {
        if (map.containsKey(key)) {
            Object value = map.get(key);
            if (value instanceof BigDecimal) {
                BigDecimal bigDecimalValue = (BigDecimal) value;
                // Convert BigDecimal to Integer
                Integer intValue = bigDecimalValue.intValue();
                map.put(key, intValue);
            }
        }
    }

    // Utility method to convert BigDecimal to String
    private void convertBigDecimalToString(Map<String, Object> map, String key) {
        if (map.containsKey(key)) {
            Object value = map.get(key);
            if (value instanceof BigDecimal) {
                BigDecimal bigDecimalValue = (BigDecimal) value;
                // Convert BigDecimal to String
                String stringValue = bigDecimalValue.toString();
                map.put(key, stringValue);
            }
        }
    }

    // Dummy method to illustrate generating 'a'
    private String generateA() {
        // Implement your logic to generate or retrieve 'a'
        return "exampleValue"; // Replace with actual logic
    }
}
