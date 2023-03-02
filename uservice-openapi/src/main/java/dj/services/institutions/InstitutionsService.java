package dj.services.institutions;

import java.util.List;

import nordigen.Integration;

public interface InstitutionsService {

    List<Integration> getListBanks(String country);
    
}
