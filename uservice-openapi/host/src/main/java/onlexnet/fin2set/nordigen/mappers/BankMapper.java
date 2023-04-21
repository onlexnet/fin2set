package onlexnet.fin2set.nordigen.mappers;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;
import onlexnet.fin2set.domain.models.Bank;
import onlexnet.fin2set.nordigen.generated.Integration;

@UtilityClass
public class BankMapper {
    
    public static Bank fromDTO(Integration integration) {
        return new Bank()
        .setId(integration.getId())
        .setName(integration.getName())
        .setLogo(integration.getLogo());
    }

    public static List<Bank> fromDTO(List<Integration> listIntegration) {
        var list = new ArrayList<Bank>();
        for (Integration integration : listIntegration) {
            list.add(fromDTO(integration));
        }
        return list;
    }
}
