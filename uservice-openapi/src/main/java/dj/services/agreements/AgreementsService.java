package dj.services.agreements;

import dj.dto.integration.AgreementData;

public interface AgreementsService {

    /**
     * @param institutionId
     * @return 
     */
    AgreementData createAgreement(String institutionId);
    
}
