package batch.org.springweb.model;
@Component
public class DefaultDAOService implements DAOService {
    private DataAccess dataAccess;

    @Autowired
    public DefaultDAOService(DataAccessFactory dataAccessFactory) {
        this.dataAccess = dataAccessFactory.createOracle();
    }

    @Override
    public void extract() {
        // Implement the extract logic here
    }
}

