package onlexnet.webapi.infra;

/**
 * String configuration property.
 */
public class StringProperty extends Property<String> {

  public StringProperty(String envName) {
    this(envName, envName);
  }

  /**
   * {@inheritDoc}
   */
  public StringProperty(String name, String envName) {
    super(name, envName);
  }

  /**
   * {@inheritDoc}
   */
  public StringProperty(String name, String envName, String defaultValue) {
    super(name, envName, defaultValue);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String parse(String value) {
    return value;
  }

}
