package batch.org.springweb.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory {

    private final DataAccessFactory dataAccessFactory;

    @Autowired
    public ServiceFactory(DataAccessFactory dataAccessFactory) {
        this.dataAccessFactory = dataAccessFactory;
    }

    public ODaoService createODaoService() {
        String oDaoServiceClassName = CustomUtil.getValue("CustomUtil.service/DAOservice.class");
        return (ODaoService) CustomUtil.instantiateClass(
                oDaoServiceClassName, 
                ODaoService.class, 
                new Class[0], 
                new Object[0], 
                getClass().getClassLoader()
        );
    }
}
