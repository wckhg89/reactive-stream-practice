package reactive.guppy;

import com.google.common.collect.Lists;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by guppy.kang on 2017. 12. 16.
 * email : guppy.kang@kakaocorp.com
 */
public class GuppyPublisher implements Publisher {

    private static final Logger logger = LoggerFactory.getLogger(observer.guppy.GuppyPublisher.class);

    private String name = "Honggu Kang";
    private String company = "ZUM internet";
    private String status = "tired";
    private int age = 29;
    private List<String> skills = Lists.newArrayList("java", "spring", "javascript");

    // Obserable 의 addObserver 와 같은 역할
    @Override
    public void subscribe(Subscriber subscriber) {

        subscriber.onSubscribe(new Subscription() {
            @Override
            public void request(long n) {
                if (n <= 0) {
                    
                }
            }

            @Override
            public void cancel() {

            }
        });
    }


    public void changeJob () {
        this.company = "Kakao corp";
        this.status = "happy";


    }

    public void newYear () {
        this.age += 1;
        this.status = "sad";

    }

    public void addSkill () {
        skills.add("webpack");

        skills.add("reactJS");

        skills.add("reactive-stream");
    }
}
