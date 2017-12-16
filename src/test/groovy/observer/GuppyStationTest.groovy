package observer

import spock.lang.Specification

/**
 * Created by guppy.kang on 2017. 12. 16.
 * email : guppy.kang@kakaocorp.com
 */
class GuppyStationTest extends Specification {

    def "구피 알림이 테스트" () {
        given:

        Guppy guppy = new Guppy()

        when:

        guppy.addSubscriber(new WarranSubscriber())
        guppy.addSubscriber(new JohnSubscriber())

        then:

        guppy.changeJob()
        guppy.newYear()

    }

}
