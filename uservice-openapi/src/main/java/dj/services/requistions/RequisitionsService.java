    package dj.services.requistions;

import java.net.URI;

public interface RequisitionsService {

    URI createConnection(String institutionId);

    String getListAccounts(String reference);
}
