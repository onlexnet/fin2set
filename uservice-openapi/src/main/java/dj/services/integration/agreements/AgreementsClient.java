package dj.services.integration.agreements;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import dj.models.dto.EndUserAgreementDTO;
import dj.models.dto.PaginatedEndUserAgreementListDTO;
import feign.Headers;
import nordigen.EndUserAgreementRequest;

@FeignClient(value = "agreement", url = "https://ob.nordigen.com/api/v2/agreements/enduser")
@Headers({
                "accept: application/json",
                "Content-Type: application/json" })
public interface AgreementsClient {

        /**
         * 
         * Method return our DTO becouse actually schema nordigen is broken and we are waiting
         * for fix
         */
        @GetMapping(value = "/")
        PaginatedEndUserAgreementListDTO getListAllAgreements(@RequestHeader("Authorization") String accessToken);

        /**
         * 
         * Method return our DTO becouse actually schema nordigen is broken and we are waiting
         * for fix
         */
        @PostMapping(value = "/")
        EndUserAgreementDTO createAgreement(@RequestHeader("Authorization") String accessToken,
                        @RequestBody EndUserAgreementRequest endUserAgreementRequest);

        /**
         * 
         * Method return our DTO becouse actually schema nordigen is broken and we are waiting
         * for fix
         */
        @GetMapping(value = "/{agreementID}")
        EndUserAgreementDTO getAgreement(@RequestHeader("Authorization") String accessToken,
                        @PathVariable UUID agreementID);

        @DeleteMapping(value = "/{agreementID}")
        void deleteAgreement(@RequestHeader("Authorization") String accessToken, @PathVariable UUID agreementID);

}
