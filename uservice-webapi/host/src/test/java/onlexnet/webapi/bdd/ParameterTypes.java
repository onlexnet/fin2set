package onlexnet.webapi.bdd;

import io.cucumber.java.ParameterType;
import onlexnet.webapi.domain.ValName;

public class ParameterTypes {

  @ParameterType(".+")
  public ValName userAlias(String value) {
    return ValName.of(value);
  }
}
