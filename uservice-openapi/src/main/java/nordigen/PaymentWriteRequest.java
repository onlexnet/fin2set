/*
 * Nordigen Account Information Services API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 2.0 (v2)
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package nordigen;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * PaymentWriteSerializer.
 */@ApiModel(description = "PaymentWriteSerializer.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaClientCodegen", date = "2023-02-25T17:56:18.166387600+01:00[Europe/Warsaw]")
public class PaymentWriteRequest {

  
  @JsonProperty("institution_id")
  private String institutionId = "SWEDBANK_SANDBOX_SANDLV22";
  
  
  @JsonProperty("payment_product")
  private Object paymentProduct;
  
  
  @JsonProperty("instructed_amount")
  private Object instructedAmount = null;
  
  
  @JsonProperty("creditor_account")
  private UUID creditorAccount = null;
  
  
  @JsonProperty("debtor_account")
  private Object debtorAccount = null;
  
  
  @JsonProperty("redirect")
  private String redirect = null;
  
  
  @JsonProperty("description")
  private String description = "GOCARDLESS";
  
  
  @JsonProperty("custom_payment_id")
  private String customPaymentId = null;
  
  
  @JsonProperty("requested_execution_date")
  private LocalDate requestedExecutionDate = null;
  
  
  @JsonProperty("periodic_payment")
  private PeriodicPaymentRequest periodicPayment = null;
  
  public PaymentWriteRequest institutionId(String institutionId) {
    this.institutionId = institutionId;
    return this;
  }

  
  /**
  * Institution ID for Payment
  * @return institutionId
  **/
  @ApiModelProperty(value = "Institution ID for Payment")
  public String getInstitutionId() {
    return institutionId;
  }
  public void setInstitutionId(String institutionId) {
    this.institutionId = institutionId;
  }
  
  public PaymentWriteRequest paymentProduct(Object paymentProduct) {
    this.paymentProduct = paymentProduct;
    return this;
  }

  
  /**
  * Payment product
  * @return paymentProduct
  **/
  @ApiModelProperty(value = "Payment product")
  public Object getPaymentProduct() {
    return paymentProduct;
  }
  public void setPaymentProduct(Object paymentProduct) {
    this.paymentProduct = paymentProduct;
  }
  
  public PaymentWriteRequest instructedAmount(Object instructedAmount) {
    this.instructedAmount = instructedAmount;
    return this;
  }

  
  /**
  * Instructed amount
  * @return instructedAmount
  **/
  @ApiModelProperty(required = true, value = "Instructed amount")
  public Object getInstructedAmount() {
    return instructedAmount;
  }
  public void setInstructedAmount(Object instructedAmount) {
    this.instructedAmount = instructedAmount;
  }
  
  public PaymentWriteRequest creditorAccount(UUID creditorAccount) {
    this.creditorAccount = creditorAccount;
    return this;
  }

  
  /**
  * Registered creditor account
  * @return creditorAccount
  **/
  @ApiModelProperty(required = true, value = "Registered creditor account")
  public UUID getCreditorAccount() {
    return creditorAccount;
  }
  public void setCreditorAccount(UUID creditorAccount) {
    this.creditorAccount = creditorAccount;
  }
  
  public PaymentWriteRequest debtorAccount(Object debtorAccount) {
    this.debtorAccount = debtorAccount;
    return this;
  }

  
  /**
  * Debtor account
  * @return debtorAccount
  **/
  @ApiModelProperty(value = "Debtor account")
  public Object getDebtorAccount() {
    return debtorAccount;
  }
  public void setDebtorAccount(Object debtorAccount) {
    this.debtorAccount = debtorAccount;
  }
  
  public PaymentWriteRequest redirect(String redirect) {
    this.redirect = redirect;
    return this;
  }

  
  /**
  * Redirect URL to your application after payment is done
  * @return redirect
  **/
  @ApiModelProperty(required = true, value = "Redirect URL to your application after payment is done")
  public String getRedirect() {
    return redirect;
  }
  public void setRedirect(String redirect) {
    this.redirect = redirect;
  }
  
  public PaymentWriteRequest description(String description) {
    this.description = description;
    return this;
  }

  
  /**
  * Payment description
  * @return description
  **/
  @ApiModelProperty(value = "Payment description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  
  public PaymentWriteRequest customPaymentId(String customPaymentId) {
    this.customPaymentId = customPaymentId;
    return this;
  }

  
  /**
  * Payment Custom Payment ID
  * @return customPaymentId
  **/
  @ApiModelProperty(value = "Payment Custom Payment ID")
  public String getCustomPaymentId() {
    return customPaymentId;
  }
  public void setCustomPaymentId(String customPaymentId) {
    this.customPaymentId = customPaymentId;
  }
  
  public PaymentWriteRequest requestedExecutionDate(LocalDate requestedExecutionDate) {
    this.requestedExecutionDate = requestedExecutionDate;
    return this;
  }

  
  /**
  * Payment Execution date (for periodic payments)
  * @return requestedExecutionDate
  **/
  @ApiModelProperty(value = "Payment Execution date (for periodic payments)")
  public LocalDate getRequestedExecutionDate() {
    return requestedExecutionDate;
  }
  public void setRequestedExecutionDate(LocalDate requestedExecutionDate) {
    this.requestedExecutionDate = requestedExecutionDate;
  }
  
  public PaymentWriteRequest periodicPayment(PeriodicPaymentRequest periodicPayment) {
    this.periodicPayment = periodicPayment;
    return this;
  }

  
  /**
  * Get periodicPayment
  * @return periodicPayment
  **/
  @ApiModelProperty(value = "")
  public PeriodicPaymentRequest getPeriodicPayment() {
    return periodicPayment;
  }
  public void setPeriodicPayment(PeriodicPaymentRequest periodicPayment) {
    this.periodicPayment = periodicPayment;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentWriteRequest paymentWriteRequest = (PaymentWriteRequest) o;
    return Objects.equals(this.institutionId, paymentWriteRequest.institutionId) &&
        Objects.equals(this.paymentProduct, paymentWriteRequest.paymentProduct) &&
        Objects.equals(this.instructedAmount, paymentWriteRequest.instructedAmount) &&
        Objects.equals(this.creditorAccount, paymentWriteRequest.creditorAccount) &&
        Objects.equals(this.debtorAccount, paymentWriteRequest.debtorAccount) &&
        Objects.equals(this.redirect, paymentWriteRequest.redirect) &&
        Objects.equals(this.description, paymentWriteRequest.description) &&
        Objects.equals(this.customPaymentId, paymentWriteRequest.customPaymentId) &&
        Objects.equals(this.requestedExecutionDate, paymentWriteRequest.requestedExecutionDate) &&
        Objects.equals(this.periodicPayment, paymentWriteRequest.periodicPayment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(institutionId, paymentProduct, instructedAmount, creditorAccount, debtorAccount, redirect, description, customPaymentId, requestedExecutionDate, periodicPayment);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentWriteRequest {\n");
    
    sb.append("    institutionId: ").append(toIndentedString(institutionId)).append("\n");
    sb.append("    paymentProduct: ").append(toIndentedString(paymentProduct)).append("\n");
    sb.append("    instructedAmount: ").append(toIndentedString(instructedAmount)).append("\n");
    sb.append("    creditorAccount: ").append(toIndentedString(creditorAccount)).append("\n");
    sb.append("    debtorAccount: ").append(toIndentedString(debtorAccount)).append("\n");
    sb.append("    redirect: ").append(toIndentedString(redirect)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    customPaymentId: ").append(toIndentedString(customPaymentId)).append("\n");
    sb.append("    requestedExecutionDate: ").append(toIndentedString(requestedExecutionDate)).append("\n");
    sb.append("    periodicPayment: ").append(toIndentedString(periodicPayment)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  
}


