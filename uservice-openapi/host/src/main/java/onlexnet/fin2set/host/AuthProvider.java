package onlexnet.fin2set.host;

/**
 * Allows to get currently logged user based on active http request.
 * If no active request is ongoing, ???
 */
public interface AuthProvider {

  AuthenticationResult current();

  sealed interface AuthenticationResult {}

  record Authenticated(String id, String name) implements AuthenticationResult {
  }

  enum Anonymous implements AuthenticationResult {
    INSTANCE
  }

}
