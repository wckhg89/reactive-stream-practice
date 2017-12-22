package rxjava;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by guppy.kang on 2017. 12. 21.
 * email : guppy.kang@kakaocorp.com
 */
public class RxJavaSample {

    private static final Logger logger = LoggerFactory.getLogger(RxJavaSample.class);

    public void sample () {
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("Hello Guppy");
                        subscriber.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                logger.info("COMPLETED!");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                logger.info("hm.. {}", s);
            }
        };


        myObservable.subscribe(mySubscriber);




    }

}
