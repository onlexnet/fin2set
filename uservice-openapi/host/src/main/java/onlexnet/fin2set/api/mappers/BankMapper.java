package onlexnet.fin2set.api.mappers;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.Bank;

@UtilityClass
public class BankMapper {

  public static onlexnet.fin2set.generated.dto.Bank toDTO(Bank bank) {
    var bankDTO = new onlexnet.fin2set.generated.dto.Bank();
    bankDTO.setId(bank.getId());
    bankDTO.setName(bank.getName());
    bankDTO.setLogo(bank.getLogo());
    return bankDTO;
  }

  public static List<onlexnet.fin2set.generated.dto.Bank> toDTO(List<Bank> bankList) {
    var list = new ArrayList<onlexnet.fin2set.generated.dto.Bank>();
    for (Bank bank : bankList) {
      list.add(toDTO(bank));
    }
    return list;
  }

}
