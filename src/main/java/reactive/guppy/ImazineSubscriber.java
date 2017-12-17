package reactive.guppy;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by guppy.kang on 2017. 12. 16.
 * email : guppy.kang@kakaocorp.com
 */
public class ImazineSubscriber implements Subscriber {


    // onSubscribe는 최초 호출되는 메소드입니다.
    // Subscriber를 사용할때에는 무조건 처음에 호출해야 하는 내용이 스펙에 등록되어있습니다.
    @Override
    public void onSubscribe(Subscription s) {

    }


    // Java Util Observer 의 update 와 같은 역할을 한다.
    @Override
    public void onNext(Object o) {

    }


    // 에러가 있으면 onError 에서 처리
    @Override
    public void onError(Throwable t) {

    }


    // 무사히 처리가 완료되면 onComplete 에서 처리
    @Override
    public void onComplete() {

    }
}