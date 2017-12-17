package observer;

import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by guppy.kang on 2017. 12. 16.
 * email : guppy.kang@kakaocorp.com
 */

@NoArgsConstructor
public class GuppyPublisher extends Observable {

    private static final Logger logger = LoggerFactory.getLogger(GuppyPublisher.class);

    private String name = "Honggu Kang";
    private String company = "ZUM internet";
    private String status = "tired";
    private int age = 29;
    private List<String> skills = Lists.newArrayList("java", "spring", "javascript");


    public void changeJob () {
        this.company = "Kakao corp";
        this.status = "happy";

        this.infoChanged("guppy have a new job.");
    }

    public void newYear () {
        this.age += 1;
        this.status = "sad";

        this.infoChanged("guppy ate 떡국");
    }

    public void addSkill () {
        skills.add("webpack");
        infoChanged("guppy learn new skill");

        skills.add("reactJS");
        infoChanged("guppy learn new skill");

        skills.add("reactive-stream");
        infoChanged("[END] guppy learn new skill");
    }


    public void infoChanged (String msg) {
        super.setChanged();
        super.notifyObservers(msg);
    }

    public void addSubscriber (Observer subscriber) {
        super.addObserver(subscriber);
    }

    public void aboutGuppy () {
        logger.info("name : {}", this.name);
        logger.info("company : {}", this.company);
        logger.info("status : {}", this.status);
        logger.info("age : {}", this.age);
    }

}
