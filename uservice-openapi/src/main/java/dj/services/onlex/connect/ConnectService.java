package dj.services.onlex.connect;

import java.net.URI;

import dj.models.CustomerDataDTO;

public interface ConnectService {

    URI createRequisition(String institutionId);

    CustomerDataDTO getInfoAboutConection(String reference);
    
}
