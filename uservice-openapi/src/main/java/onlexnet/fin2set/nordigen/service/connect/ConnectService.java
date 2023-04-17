package onlexnet.fin2set.nordigen.service.connect;

import java.net.URI;

import onlexnet.fin2set.domain.models.BankUserDetailsConnection;

public interface ConnectService {

    URI createLinkToConnect(String bankID);

    BankUserDetailsConnection getInfoAboutConection(String reference);
    
}
