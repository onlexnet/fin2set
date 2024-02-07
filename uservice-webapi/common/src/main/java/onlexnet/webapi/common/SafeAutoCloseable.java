package onlexnet.webapi.common;

/**
 * AutoCloseable which doesn't be to close with handling exception from close
 * method.
 */
@FunctionalInterface
public interface SafeAutoCloseable extends AutoCloseable {
  void close();
}

