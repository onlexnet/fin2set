package dj.services.onlex.connect;

import java.net.URI;

import dj.models.CustomerDataDTO;

public interface ConnectService {

    URI createLinkToConnect(String institutionId);

    CustomerDataDTO getInfoAboutConection(String reference);
    
}
