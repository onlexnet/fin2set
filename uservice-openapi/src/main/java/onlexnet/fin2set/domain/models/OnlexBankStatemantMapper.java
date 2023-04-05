package onlexnet.fin2set.domain.models;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.api.dto.AccountDTO;
import onlexnet.fin2set.api.dto.NordigenBankStatemant;

@Service
public class OnlexBankStatemantMapper {

    public OnlexBankStatement toOnlexBankStatement(NordigenBankStatemant nordigenBankStatemant, AccountDTO account) {

        return new OnlexBankStatement()
        .setOwnerName(account.getOwnerName())
        .setDepositName(account.getId())
        .setBookedTransactions(nordigenBankStatemant.getTransactions().getBooked());
    }
    
}
