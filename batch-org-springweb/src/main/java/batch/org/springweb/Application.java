package batch.org.springweb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import batch.org.springweb.service.QueryService;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private QueryService queryService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            // Hardcoded query
            String query = "SELECT * FROM app";

            // Execute the query without parameters
            List<Map<String, Object>> results = queryService.fetchQueryResults(query);

            // Output results
            if (results.isEmpty()) {
                logger.info("No records found.");
            } else {
                results.forEach(result -> {
                    logger.info("Record: {}", result);
                });
            }
        } catch (Exception e) {
            logger.error("Error executing query: {}", e.getMessage(), e);
        }
    }
}
