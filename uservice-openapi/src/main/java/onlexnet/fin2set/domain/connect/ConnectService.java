package onlexnet.fin2set.domain.connect;

import java.net.URI;

import onlexnet.fin2set.domain.models.CustomerData;

public interface ConnectService {

    URI createLinkToConnect(String bankID);

    CustomerData getInfoAboutConection(String reference);
    
}
