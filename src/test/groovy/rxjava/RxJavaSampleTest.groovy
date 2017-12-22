package rxjava

import spock.lang.Specification

/**
 * Created by guppy.kang on 2017. 12. 21.
 * email : guppy.kang@kakaocorp.com
 */
class RxJavaSampleTest extends Specification {

    def "sample Test "() {
        given:

        RxJavaSample rxJavaSample = new RxJavaSample()

        when:

        rxJavaSample.sample()

        then:

        true

    }
}
