package onlexnet.webapi.bdd;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;

import onlex.webapi.ViewGql;
import onlexnet.webapi.SafeAutoCloseable;

@Component
public class Facts {

  private final Lock instanceLock = new ReentrantLock();

  public ViewGql currentView;

  public void on(On event) {

    instanceLock.lock();

    try (SafeAutoCloseable unlock = () -> instanceLock.unlock()) {

      switch (event) {
        case On.OnViewEvent it: {
          currentView = it.value;
          break;
        }
        default: {
          // noop
          System.out.println("nothing");
        }
      }
    }
  }

  sealed interface On {

    record OnViewEvent(ViewGql value) implements On {
    }

  }
}
