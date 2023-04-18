package onlexnet.fin2set.domain.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Bank {

  private String id;

  private String name;

  private String logo;
}
