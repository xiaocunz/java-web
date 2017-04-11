import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zxiaocun on 2017/3/26.
 */
public class HelloWorld {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);


    public static void main(String[] args) {
        logger.error("hello");
        logger.info("info");
        logger.debug("debug");
    }
}
