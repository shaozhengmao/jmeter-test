import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chenjunlong on 17/4/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class BaseTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void before() {
        logger.info("before test...");
    }

    @After
    public void after() {
        logger.info("after test...");
    }
}