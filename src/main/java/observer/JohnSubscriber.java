package observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by guppy.kang on 2017. 12. 16.
 * email : guppy.kang@kakaocorp.com
 */
public class JohnSubscriber implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(JohnSubscriber.class);

    @Override
    public void update(Observable o, Object info) {
        logger.info("oh..? - {}", info);

        if (o instanceof GuppyPublisher) {
            ((GuppyPublisher) o).aboutGuppy();
        }

        logger.info("=========== 9분선 =============");
    }
}
