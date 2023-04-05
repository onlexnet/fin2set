package onlexnet.fin2set.domain.models;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;
import onlexnet.fin2set.nordigen.models.NordigenBankStatemant.Booked;

@Data
@Accessors(chain = true)
public class BankStatement {

    private String ownerName;
    private UUID depositName;
    private List<Booked> bookedTransactions;

}
