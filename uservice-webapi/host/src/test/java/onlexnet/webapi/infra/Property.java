package onlexnet.webapi.infra;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Property<T> {

  /**
   * Property's name as a Java Property.
   */
  private final String name;

  /**
   * Property's name as a environment variable.
   */
  private final String envName;

  /**
   * Default value.
   */
  private final T defaultValue;

  Property(String name, String envName) {
    this(name, envName, null);
    var actual = this.get();
    Objects.requireNonNull(actual, () -> "Environment value for [" + envName + "] does not exist.");
  }

  /**
   * Instantiates a new configuration property.
   *
   * @param name Java property name.
   * @param envName Environment variable name.
   * @param defaultValue Default value.
   */
  Property(String name, String envName, T defaultValue) {
    this.name = name;
    this.envName = envName;
    this.defaultValue = defaultValue;
  }

  /**
   * Gets the Java property's name.
   * @return Name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the environment variable's name.
   * @return Name.
   */
  public String getEnvName() {
    return this.envName;
  }

  /**
   * Gets the value defined by system property first, then env variable or sticks to default.
   *
   * @return Value from system property (1st) or env variable (2nd) or default (last).
   */
  public T get() {
    String propValue = System.getProperty(this.name);
    if (propValue != null && !propValue.trim().isEmpty()) {
      try {
        return this.parse(propValue);
      } catch (IllegalArgumentException e) {
        log.warn(String.format("Invalid value in property: %s", this.name));
        // OK, we tried. Falling back to system environment variable.
      }
    }

    String envValue = System.getenv(this.envName);
    if (envValue != null && !envValue.trim().isEmpty()) {
      try {
        return this.parse(envValue);
      } catch (IllegalArgumentException e) {
        log.warn(String.format("Invalid value in environment variable: %s", this.envName));
        // OK, we tried. Falling back to default.
      }
    }

    return this.defaultValue;
  }

  /**
   * Parses the value to the specific type.
   * @param value String value to be parsed.
   * @return Value in the specific type.
   */
  protected abstract T parse(String value);
}
