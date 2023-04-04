package onlexnet.fin2set.api;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import onlexnet.fin2set.domain.models.CustomerDataDTO;
import onlexnet.fin2set.domain.services.onlex.connect.ConnectService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/integration")
public class OnlexConnectApi {

    private final ConnectService connectService;

    @GetMapping("/connection")
    ResponseEntity<URI> createConection(@RequestParam String institutionId) {
        return ResponseEntity.status(HttpStatus.FOUND).location(connectService.createLinkToConnect(institutionId))
                .build();
    }

    /**
     * Endpoint under which nordigen sends us the client after the authorization on
     * the bank's side has been completed
     * 
     * Nordigen adds the ref parameter, which is the UUID reference generated
     * by us, and we assign it to the received id in order to replace them and
     * execute a query that will return a complete order document
     */
    @GetMapping("/info")
    ResponseEntity<CustomerDataDTO> getInfoAboutConection(@RequestParam(name = "ref") String reference) {
        return ResponseEntity.ok(connectService.getInfoAboutConection(reference));
    }

}
