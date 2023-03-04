    package dj.services.requistions;

import java.net.URI;

public interface RequistionsService {

    URI createConnection(String institutionId);

    String getListAccounts(String reference);
}
