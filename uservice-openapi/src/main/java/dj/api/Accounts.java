package dj.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class Accounts {

    String access = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjc2MjM2MTM0LCJqdGkiOiIzOWFmYWM5ZTE0YzQ0NGU2YTA3YjUxYTE5YzA0MTc0YiIsImlkIjoyMjk2Miwic2VjcmV0X2lkIjoiNjI4ODEyZmUtOWE5OS00ZTUwLThiOGEtYmJmZjMzMDUwYmM3IiwiYWxsb3dlZF9jaWRycyI6WyIwLjAuMC4wLzAiLCI6Oi8wIl19.sjNInygfdqmAezfKwbiI-r0kE6zi7sc7J0B-u7K6Ka4";

    @GetMapping("/transactions")
    public Object getTransactions(final RestTemplate restTemplate) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", access);

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("https://ob.nordigen.com/api/v2/accounts/7e944232-bda9-40bc-b784-660c7ab5fe78/transactions/", HttpMethod.GET, entity, Map.class);

        return response.getBody();
    }

    @GetMapping("/details")
    public Object getDetails(final RestTemplate restTemplate) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", access);

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("https://ob.nordigen.com/api/v2/accounts/7e944232-bda9-40bc-b784-660c7ab5fe78/details/", HttpMethod.GET, entity, Map.class);

        return response.getBody();
    }

    @GetMapping("/balances")
    public Object getBalances(final RestTemplate restTemplate) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", access);

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("https://ob.nordigen.com/api/v2/accounts/7e944232-bda9-40bc-b784-660c7ab5fe78/balances/", HttpMethod.GET, entity, Map.class);

        return response.getBody();
    }

    @GetMapping("/metadata")
    public Object getMetadata(final RestTemplate restTemplate) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", access);

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<Map> response = restTemplate.exchange("https://ob.nordigen.com/api/v2/accounts/7e944232-bda9-40bc-b784-660c7ab5fe78", HttpMethod.GET, entity, Map.class);

        return response.getBody();
    }
}
