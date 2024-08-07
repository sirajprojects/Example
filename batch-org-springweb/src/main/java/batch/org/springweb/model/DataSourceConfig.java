package batch.org.springweb.model;
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@//king/abc");
        dataSource.setUsername("user");
        dataSource.setPassword("user");
        return dataSource;
    }
}

