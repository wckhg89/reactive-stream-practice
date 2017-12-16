package observer;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by guppy.kang on 2017. 12. 16.
 * email : guppy.kang@kakaocorp.com
 */

@Setter
public class WarranSubscriber implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(WarranSubscriber.class);

    @Override
    public void update(Observable o, Object info) {

        logger.info("hmm.. - {}", info);

        if (o instanceof Guppy) {
            ((Guppy) o).aboutGuppy();
        }


    }
}
