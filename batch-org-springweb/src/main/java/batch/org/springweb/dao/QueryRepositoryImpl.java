package batch.org.springweb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QueryRepositoryImpl implements QueryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> executeQuery(String query, Object... params) {
        System.out.println("Executing query: " + query);
        List<Map<String, Object>> results = jdbcTemplate.queryForList(query, params);
        System.out.println("Query executed. Number of records: " + results.size());
        return results;
    }
}
