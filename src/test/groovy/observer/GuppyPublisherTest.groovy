package observer

import observer.guppy.GuppyPublisher
import observer.guppy.JohnSubscriber
import observer.guppy.WarranSubscriber
import spock.lang.Specification

/**
 * Created by guppy.kang on 2017. 12. 16.
 * email : guppy.kang@kakaocorp.com
 */
class GuppyPublisherTest extends Specification {

    def "구피 알림이 테스트" () {
        given:

        GuppyPublisher guppy = new GuppyPublisher()

        when:

        guppy.addSubscriber(new WarranSubscriber())
        guppy.addSubscriber(new JohnSubscriber())

        then:

        guppy.changeJob()
        guppy.newYear()
        guppy.addSkill()

    }

}
