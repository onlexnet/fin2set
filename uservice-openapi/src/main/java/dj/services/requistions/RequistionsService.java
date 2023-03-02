package dj.services.requistions;

import org.springframework.http.ResponseEntity;

public interface RequistionsService {

    ResponseEntity<?> createConnection(String institutionId);
    
}
