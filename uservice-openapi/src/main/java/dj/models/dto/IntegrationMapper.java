package dj.models.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dj.models.dto.enum_dto.CountryEnumDTO;
import nordigen.Integration;

@Service
public class IntegrationMapper {
    
    public IntegrationDTO toDTO(Integration integration) {
        return new IntegrationDTO()
        .setId(integration.getId())
        .setName(integration.getName())
        .setBic(integration.getBic())
        .setTransactionTotalDays(integration.getTransactionTotalDays())
        .setCountries(CountryEnumDTO.fromValue(integration.getCountries()))
        .setLogo(integration.getLogo());
    }

    public List<IntegrationDTO> toDTO(List<Integration> listIntegration) {
        var list = new ArrayList<IntegrationDTO>();
        for (Integration integration : listIntegration) {
            list.add(toDTO(integration));
        }
        return list;
    }
}
