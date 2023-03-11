package dj.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dj.services.requistions.RequisitionsService;
import lombok.RequiredArgsConstructor;
import nordigen.PaginatedRequisitionV2List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/requisitions")
public class RequisitionsApi {

    private final RequisitionsService requisitionsService;

    @GetMapping("/")
    ResponseEntity<PaginatedRequisitionV2List> getListAllRequisitions() {
        return ResponseEntity.ok(requisitionsService.getListAllRequisitions());
    }
    
    
}
