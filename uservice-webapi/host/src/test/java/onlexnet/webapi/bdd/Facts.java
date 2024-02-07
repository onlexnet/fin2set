package onlexnet.webapi.bdd;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;

import onlex.webapi.ViewGql;
import onlexnet.webapi.common.SafeAutoCloseable;

@Component
public class Facts {

  private final Lock instanceLock = new ReentrantLock();

  public ViewGql currentView;
  public String lastResponse;

  public void on(On event) {

    instanceLock.lock();

    try (SafeAutoCloseable unlock = () -> instanceLock.unlock()) {

      switch (event) {
        case On.ViewEvent it: {
          currentView = it.value;
          break;
        }
        case On.ChatLastResult it: {
          lastResponse = it.value();
        }
        default: {
          // noop
          System.out.println("nothing");
        }
      }
    }
  }

  sealed interface On {

    record ViewEvent(ViewGql value) implements On { }

    record ChatLastResult(String value) implements On { }

  }
}
