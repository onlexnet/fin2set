package dj.models;

import java.util.List;
import java.util.UUID;

import dj.models.NordigenBankStatemant.Booked;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OnlexBankStatement {

    private String ownerName;
    private UUID depositName;
    private List<Booked> bookedTransactions;

}
