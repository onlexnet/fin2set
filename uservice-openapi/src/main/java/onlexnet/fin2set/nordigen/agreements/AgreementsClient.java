package onlexnet.fin2set.nordigen.agreements;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import onlexnet.fin2set.nordigen.generated.EndUserAgreement;
import onlexnet.fin2set.nordigen.generated.EndUserAgreementRequest;
import onlexnet.fin2set.nordigen.generated.PaginatedEndUserAgreementList;

@FeignClient(value = "agreement", url = "https://ob.nordigen.com/api/v2/agreements/enduser")
@Headers({
                "accept: application/json",
                "Content-Type: application/json" })
public interface AgreementsClient {

        @GetMapping(value = "/")
        ResponseEntity<PaginatedEndUserAgreementList> getListAllAgreements(
                        @RequestHeader("Authorization") String accessToken);

        @PostMapping(value = "/")
        ResponseEntity<EndUserAgreement> createAgreement(
                        @RequestHeader("Authorization") String accessToken,
                        @RequestBody EndUserAgreementRequest endUserAgreementRequest);

        @GetMapping(value = "/{agreementID}")
        ResponseEntity<EndUserAgreement> getAgreement(
                        @RequestHeader("Authorization") String accessToken,
                        @PathVariable UUID agreementID);

        @DeleteMapping(value = "/{agreementID}")
        ResponseEntity<Object> deleteAgreement(
                        @RequestHeader("Authorization") String accessToken,
                        @PathVariable UUID agreementID);

}
