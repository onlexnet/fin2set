package dj.services.institutions;

import java.util.List;

import nordigen.Integration;

public interface InstitututionsService {

    List<Integration> getListBanks(String country);
    
}
