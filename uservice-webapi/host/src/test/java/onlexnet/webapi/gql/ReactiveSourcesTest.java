package onlexnet.webapi.gql;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import onlex.webapi.ViewGql;

public class ReactiveSourcesTest {
  
  @Test
  @SneakyThrows
  void testMe() {
    var sut = new ReactiveSources();
    var result = new ConcurrentLinkedQueue<ViewGql>();
    sut.observed().subscribe(it -> result.add(it));
    sut.publish(ViewGql.VIEW1);
    Thread.sleep(100);
    Assertions.assertThat(result).containsExactly(ViewGql.CHAT, ViewGql.VIEW1);
  }
}
