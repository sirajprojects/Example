package batch.org.springweb.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.Map;

@Component
public class ServiceFactory {
    private static final Logger logger = LoggerFactory.getLogger(ServiceFactory.class);
    private final DAOService daoService;

    @Autowired
    public ServiceFactory(DAOService daoService) {
        this.daoService = daoService;
    }

    public DAOService createDAOService() {
        String className = "batch.org.springweb.examples.DefaultDAOService";
        try {
            return (DAOService) Class.forName(className)
                                     .getDeclaredConstructor()
                                     .newInstance();
        } catch (Exception e) {
            logger.error("Error instantiating class: " + className, e);
            return null;
        }
    }
}

