package reactive

import reactive.stack.StackPublisher
import reactive.stack.StackSubscriber
import spock.lang.Specification

/**
 * Created by guppy.kang on 2017. 12. 17.
 * email : guppy.kang@kakaocorp.com
 */
class StackReactiveTest extends Specification {

    def "Reactive Stream 테스트" () {
        given:

        StackPublisher stackPublisher = new StackPublisher()
        StackSubscriber stackSubscriber = new StackSubscriber()

        when:

        stackPublisher.subscribe(stackSubscriber)
        
        then:

        true
    }
}
