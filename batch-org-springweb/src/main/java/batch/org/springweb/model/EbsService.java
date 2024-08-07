package batch.org.springweb.model;
@Component
public class EbsService implements DataAccess {
    private static final Logger logger = LoggerFactory.getLogger(EbsService.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EbsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void extract() {
        String table = "abc";
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM " + table);
        if (!results.isEmpty()) {
            Map<String, Object> firstRow = results.get(0);
            logger.info("Column names: {}", firstRow.keySet());
            for (Map<String, Object> row : results) {
                logger.info("Row: {}", row);
            }
        }
    }
}
