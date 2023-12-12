package onlexnet.webapi.bdd;

import io.cucumber.java.ParameterType;
import onlex.webapi.ViewGql;
import onlexnet.webapi.domain.ValName;

public class ParameterTypes {

  @ParameterType(".+")
  public ValName userAlias(String value) {
    return ValName.of(value);
  }

  @ParameterType(".+")
  public ViewGql viewName(String value) {
    return ViewGql.valueOf(value);
  }
}
