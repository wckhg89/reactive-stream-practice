package reactive.stack;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guppy.kang on 2017. 12. 17.
 * email : guppy.kang@kakaocorp.com
 */
public class StackSubscriber implements Subscriber {

    private static final Logger logger = LoggerFactory.getLogger(StackSubscriber.class);
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;

        subscription.request(1);
    }

    @Override
    public void onNext(Object o) {
        logger.info("onNext - {}", o);

        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("onError - {}", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        logger.info("COMPLETE");
    }

}
