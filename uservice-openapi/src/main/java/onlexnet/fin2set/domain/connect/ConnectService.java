package onlexnet.fin2set.domain.connect;

import java.net.URI;

import onlexnet.fin2set.domain.models.CustomerDataDTO;

public interface ConnectService {

    URI createLinkToConnect(String institutionId);

    CustomerDataDTO getInfoAboutConection(String reference);
    
}
