package batch.org.springweb.dao;

import java.util.List;
import java.util.Map;

public interface QueryRepository {
    List<Map<String, Object>> executeQuery(String query, Object... params);
}
