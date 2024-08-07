package batch.org.springweb.model;
@Component
public class DataAccessFactory {
    private final DataSource dataSource;

    @Autowired
    public DataAccessFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataAccess createOracle() {
        String accessClass = "batch.org.springweb.examples.EbsService";
        try {
            return (DataAccess) Class.forName(accessClass)
                                    .getDeclaredConstructor(DataSource.class)
                                    .newInstance(dataSource);
        } catch (Exception e) {
            // Handle instantiation error
            return null;
        }
    }
}

