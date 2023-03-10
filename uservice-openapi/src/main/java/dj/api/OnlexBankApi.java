package dj.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dj.models.OnlexBankStatement;
import dj.services.onlex.OnlexService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/onlex")
public class OnlexBankApi {

    private final OnlexService onlexService;

    
    @GetMapping("/bankstatement")
    ResponseEntity<OnlexBankStatement> createConection(@RequestParam String accountID) {
        return ResponseEntity.ok(onlexService.getOnlxBankStatement(accountID));
    }
    
    

}
