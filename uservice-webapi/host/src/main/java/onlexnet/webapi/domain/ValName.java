package onlexnet.webapi.domain;

import org.apache.commons.lang3.StringUtils;

import lombok.Value;
import lombok.experimental.Accessors;

/**
 * TBD.
 */
@Value
@Accessors(fluent = true)
public final class ValName {

  private static final ValName EMPTY = new ValName(null);

  private String value;

  /**
   * TBD.
   */
  public static ValName of(String value) {

    if (StringUtils.isBlank(value)) {
      return EMPTY;
    }
    return new ValName(value);
  }

  public static ValName empty() {
    return EMPTY;
  }
}
