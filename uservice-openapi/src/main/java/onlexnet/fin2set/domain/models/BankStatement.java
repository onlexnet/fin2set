package onlexnet.fin2set.domain.models;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BankStatement {

    private String ownerName;

    private UUID depositName;

    private List<Transaction> bookedTransactions;

}
