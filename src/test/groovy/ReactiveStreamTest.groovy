import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by guppy.kang on 2017. 12. 16.
 * email : guppy.kang@kakaocorp.com
 */
class ReactiveStreamTest extends Specification {

    @Unroll
    def "#DESC 돌려보는거야" () {
        given:

        String str = new String()

        when:

        str = STR_STR

        then:

        STR_STR_STR == STR_STR

        where:

        DESC | STR_STR | STR_STR_STR

        "guppy" | "guppy" | "guppy"
    }

}
