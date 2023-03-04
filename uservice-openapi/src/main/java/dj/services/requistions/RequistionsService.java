package dj.services.requistions;

import java.net.URI;

public interface RequistionsService {

    ResponseEntity<?> createConnection(String institutionId);

    String getListAccounts(String reference);
    

    URI createConnection(String institutionId);

    String getListAccounts(String reference);
>>>>>>> 979c102c51f40abd2d6fff37b3cced89c9095325
}
