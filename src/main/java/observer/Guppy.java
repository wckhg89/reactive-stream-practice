package observer;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by guppy.kang on 2017. 12. 16.
 * email : guppy.kang@kakaocorp.com
 */

@NoArgsConstructor
public class Guppy extends Observable {

    private String name = "Honggu Kang";
    private String company = "ZUM interernet";
    private String status = "tired";
    private int age = 29;


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

    public void infoChanged (String msg) {
        super.setChanged();
        super.notifyObservers(msg);
    }

    public void addSubscriber (Observer subscriber) {
        super.addObserver(subscriber);
    }

}
