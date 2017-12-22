package rxjava.guppy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Subscriber;

/**
 * Created by guppy.kang on 2017. 12. 21.
 * email : guppy.kang@kakaocorp.com
 */
public class RxImazineSubscriber <T> extends Subscriber <T>{

    private static final Logger logger = LoggerFactory.getLogger(RxImazineSubscriber.class);

    @Override
    public void onCompleted() {
        logger.info("WOW COMPLETED!");
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T info) {
        logger.info("hm.. {}", info);
    }
}
