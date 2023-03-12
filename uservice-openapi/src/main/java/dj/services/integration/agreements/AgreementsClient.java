package dj.services.integration.agreements;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import nordigen.EndUserAgreement;
import nordigen.EndUserAgreementRequest;
import nordigen.PaginatedEndUserAgreementList;

@FeignClient(value = "agreement", url = "https://ob.nordigen.com/api/v2/agreements/enduser")
@Headers({
                "accept: application/json",
                "Content-Type: application/json" })
public interface AgreementsClient {

        @GetMapping(value = "/")
        PaginatedEndUserAgreementList getListAllAgreements(@RequestHeader("Authorization") String accessToken);

        @PostMapping(value = "/")
        EndUserAgreement createAgreement(@RequestHeader("Authorization") String accessToken, @RequestBody EndUserAgreementRequest endUserAgreementRequest);

        @GetMapping(value = "/{agreementID}")
        EndUserAgreement getAgreement(@RequestHeader("Authorization") String accessToken, @PathVariable String agreementID);

        @DeleteMapping(value = "/{agreementID}")
        void deleteAgreement(@RequestHeader("Authorization") String accessToken, @PathVariable String agreementID);

}
