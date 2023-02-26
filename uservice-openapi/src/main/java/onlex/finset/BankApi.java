package onlex.finset;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Data;

public interface BankApi {

    BankStatement.Doc read();
    

}

/** TBD. */
interface BankStatement {
  
  /** Document. */
  @Data
  class Doc {
    /** e.g. 2020/01 */
    private String name;
    private String depositName;
    private List<Position> positions;
  }

  /** TBD. */
  @Data
  public static class Position {
    private int no;
    private LocalDate date;
    private double amount;
    private String notes;
  }
}

