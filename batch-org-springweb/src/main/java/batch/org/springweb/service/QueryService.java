package batch.org.springweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import batch.org.springweb.dao.QueryRepository;

@Service
public class QueryService {

    @Autowired
    private QueryRepository queryRepository;

    public List<Map<String, Object>> fetchQueryResults(String query, Object... params) {
        return queryRepository.executeQuery(query, params);
    }
}
