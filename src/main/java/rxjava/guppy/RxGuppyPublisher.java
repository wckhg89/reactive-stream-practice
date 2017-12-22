package rxjava.guppy;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;

import java.util.List;

/**
 * Created by guppy.kang on 2017. 12. 21.
 * email : guppy.kang@kakaocorp.com
 */
public class RxGuppyPublisher<T> extends Observable<T> {

    private static final Logger logger = LoggerFactory.getLogger(RxGuppyPublisher.class);

    private String name = "Honggu Kang";
    private String company = "ZUM internet";
    private String status = "tired";
    private int age = 29;
    private List<String> skills = Lists.newArrayList("java", "spring", "javascript");
    
    public void addSubscriber () {

    }




    /**
     * Creates an Observable with a Function to execute when it is subscribed to.
     * <p>
     * <em>Note:</em> Use {@link #unsafeCreate(OnSubscribe)} to create an Observable, instead of this constructor,
     * unless you specifically have a need for inheritance.
     *
     * @param f {@link OnSubscribe} to be executed when {@link #subscribe(Subscriber)} is called
     */
    protected RxGuppyPublisher(OnSubscribe<T> f) {
        super(f);
    }
}
