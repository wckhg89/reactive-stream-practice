package reactive.stack;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

/**
 * Created by guppy.kang on 2017. 12. 17.
 * email : guppy.kang@kakaocorp.com
 */
public class StackPublisher implements Publisher {

    private static final Logger logger = LoggerFactory.getLogger(StackPublisher.class);

    private Stack<Integer> stack;

    public StackPublisher() {
        this.stack = new Stack<>();

    }

    @Override
    public void subscribe(Subscriber subscriber) {
        initStack();

        subscriber.onSubscribe(new Subscription() {
            public void request(long l) {

                logger.info("Request : {}", l);

                if(l < 0) {
                    notifyException();
                    return;
                }

                for(int i = 1 ; i <= l ; i++) {
                    if(stack.empty()) {
                        notifyComplete();

                        return;
                    }

                    notifyNextEvent();
                }
            }

            private void notifyNextEvent() {
                subscriber.onNext(stack.pop());
            }

            private void notifyComplete() {
                subscriber.onComplete();
            }

            private void notifyException() {
                subscriber.onError(new Exception("  0 이상의 숫자를 넣어야 합니다"));
            }

            public void cancel() {

            }
        });
    }

    private void initStack() {
        for(int i = 0; i < 10 ; i++){
            stack.push(i);
        }
    }
}
