package onlexnet.webapi.plaid;

public interface PlaidService {
  
  String createLinkToken();

  String exchangeLinkToken(String linkToken);
}
