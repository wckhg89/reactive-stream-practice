package observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guppy.kang on 2017. 12. 16.
 * email : guppy.kang@kakaocorp.com
 */
public class JohnSubscriber implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(JohnSubscriber.class);

    @Override
    public void update(Observable o, Object info) {
        logger.info("oh..? - {}", info);

        if (info instanceof String) {
            boolean isEnd = this.parsingInfoForEndSignal((String) info);

            if (isEnd) {
                logger.info("END!");
            }
        }


        if (o instanceof GuppyPublisher) {
            ((GuppyPublisher) o).aboutGuppy();
        }

        logger.info("=========== 9분선 =============");
    }

    private boolean parsingInfoForEndSignal (String info) {
        String regex = "^(\\[([a-zA-Z|\\s]*)(\\/[a-zA-Z]*)?\\])?(.*)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(info);

        if (matcher.find()) {
            String endSignal = matcher.group(2); // 두번째 괄호

            if ("END".equals(endSignal)) {
                return true;
            }
        }

        return false;
    }
}
