package onlexnet.webapi.domain.models;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValNameTest {

    @Test
    void testOf() {

      Assertions
        .assertThat(ValName.of(null))
        .isSameAs(ValName.empty());

        Assertions
        .assertThat(ValName.of(""))
        .isSameAs(ValName.empty());
    }
}
