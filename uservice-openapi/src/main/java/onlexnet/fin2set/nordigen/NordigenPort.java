package onlexnet.fin2set.nordigen;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import onlexnet.fin2set.domain.models.Bank;
import onlexnet.fin2set.domain.models.BankStatement;
import onlexnet.fin2set.domain.models.BankUserDetailsConnection;

public interface NordigenPort {

  /**
   * @param bankID
   * @return the http address to which the user should be redirected, where he will log in to his
   * bank and give us permission to access information on his bank accounts.
   */
  URI createLinkToConnect(String bankID);

  /**
   * 
   * @param reference
   * @return in the createLinkToConnect method, we declare the http address to which nordigen will redirect
   * the user when he successfully logs in and gives us the necessary consent. Nordigen will add a reference
   * value to our address, after which we will identify the user and send him his connection details.
   */
  BankUserDetailsConnection getInfoAboutConection(String reference);

  /**
   * 
   * @param connectionID
   * @return based on the id of the connection, it will search and return data about the connection
   */
  BankUserDetailsConnection getBankUserDetailsConnection(UUID connectionID);

  /**
   * 
   * @param country
   * @return based on the country code, it will return a list of banks cooperating with nordigen in a given country
   */
  List<Bank> getListBanks(String country);

  /**
   * 
   * @param bankID
   * @return will search for a given bank based on its id
   */
  Bank getBank(String bankID);

  /**
   * 
   * @param accountID
   * @return transactions on the selected account, account ID and name of the owner
   */
  BankStatement getBankStatement(UUID accountID);


}
