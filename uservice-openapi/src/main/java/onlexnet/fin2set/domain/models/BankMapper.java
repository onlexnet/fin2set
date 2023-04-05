package onlexnet.fin2set.domain.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import onlexnet.fin2set.api.dto.enum_dto.CountryEnumDTO;
import onlexnet.fin2set.nordigen.generated.Integration;

@Service
public class BankMapper {
    
    public Bank toDTO(Integration integration) {
        return new Bank()
        .setId(integration.getId())
        .setName(integration.getName())
        .setBic(integration.getBic())
        .setTransactionTotalDays(integration.getTransactionTotalDays())
        .setCountries(CountryEnumDTO.fromValue(integration.getCountries()))
        .setLogo(integration.getLogo());
    }

    public List<Bank> toDTO(List<Integration> listIntegration) {
        var list = new ArrayList<Bank>();
        for (Integration integration : listIntegration) {
            list.add(toDTO(integration));
        }
        return list;
    }
}
