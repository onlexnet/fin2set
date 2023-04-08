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

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Create requisition.
 */@ApiModel(description = "Create requisition.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaClientCodegen", date = "2023-02-25T17:56:18.166387600+01:00[Europe/Warsaw]")
public class SpectacularRequisitionV2 {

  
  @JsonProperty("id")
  private UUID id = null;
  
  
  @JsonProperty("created")
  private OffsetDateTime created = null;
  
  
  @JsonProperty("redirect")
  private String redirect = null;
  
  
  @JsonProperty("status")
  private Object status = null;
  
  
  @JsonProperty("institution_id")
  private String institutionId = null;
  
  
  @JsonProperty("agreement")
  private UUID agreement = null;
  
  
  @JsonProperty("reference")
  private String reference = null;
  
  
  @JsonProperty("accounts")
  private List<Object> accounts = null;
  
  
  @JsonProperty("user_language")
  private String userLanguage = null;
  
  
  @JsonProperty("link")
  private String link = "https://ob.nordigen.com/psd2/start/3fa85f64-5717-4562-b3fc-2c963f66afa6/{$INSTITUTION_ID}";
  
  
  @JsonProperty("ssn")
  private String ssn = null;
  
  
  @JsonProperty("account_selection")
  private Boolean accountSelection = false;
  
  
  @JsonProperty("redirect_immediate")
  private Boolean redirectImmediate = false;
  
  
  /**
  * Get id
  * @return id
  **/
  @ApiModelProperty(value = "")
  public UUID getId() {
    return id;
  }
  
  
  /**
  * The date &amp; time at which the requisition was created.
  * @return created
  **/
  @ApiModelProperty(value = "The date & time at which the requisition was created.")
  public OffsetDateTime getCreated() {
    return created;
  }
  
  public SpectacularRequisitionV2 redirect(String redirect) {
    this.redirect = redirect;
    return this;
  }

  
  /**
  * redirect URL to your application after end-user authorization with ASPSP
  * @return redirect
  **/
  @ApiModelProperty(required = true, value = "redirect URL to your application after end-user authorization with ASPSP")
  public String getRedirect() {
    return redirect;
  }
  public void setRedirect(String redirect) {
    this.redirect = redirect;
  }
  
  
  /**
  * status of this requisition
  * @return status
  **/
  @ApiModelProperty(value = "status of this requisition")
  public Object getStatus() {
    return status;
  }
  
  public SpectacularRequisitionV2 institutionId(String institutionId) {
    this.institutionId = institutionId;
    return this;
  }

  
  /**
  * an Institution ID for this Requisition
  * @return institutionId
  **/
  @ApiModelProperty(required = true, value = "an Institution ID for this Requisition")
  public String getInstitutionId() {
    return institutionId;
  }
  public void setInstitutionId(String institutionId) {
    this.institutionId = institutionId;
  }
  
  public SpectacularRequisitionV2 agreement(UUID agreement) {
    this.agreement = agreement;
    return this;
  }

  
  /**
  * EUA associated with this requisition
  * @return agreement
  **/
  @ApiModelProperty(value = "EUA associated with this requisition")
  public UUID getAgreement() {
    return agreement;
  }
  public void setAgreement(UUID agreement) {
    this.agreement = agreement;
  }
  
  public SpectacularRequisitionV2 reference(String reference) {
    this.reference = reference;
    return this;
  }

  
  /**
  * additional ID to identify the end user
  * @return reference
  **/
  @ApiModelProperty(value = "additional ID to identify the end user")
  public String getReference() {
    return reference;
  }
  public void setReference(String reference) {
    this.reference = reference;
  }
  
  
  /**
  * array of account IDs retrieved within a scope of this requisition
  * @return accounts
  **/
  @ApiModelProperty(value = "array of account IDs retrieved within a scope of this requisition")
  public List<Object> getAccounts() {
    return accounts;
  }
  
  public SpectacularRequisitionV2 userLanguage(String userLanguage) {
    this.userLanguage = userLanguage;
    return this;
  }

  
  /**
  * A two-letter country code (ISO 639-1)
  * @return userLanguage
  **/
  @ApiModelProperty(value = "A two-letter country code (ISO 639-1)")
  public String getUserLanguage() {
    return userLanguage;
  }
  public void setUserLanguage(String userLanguage) {
    this.userLanguage = userLanguage;
  }
  
  
  /**
  * link to initiate authorization with Institution
  * @return link
  **/
  @ApiModelProperty(value = "link to initiate authorization with Institution")
  public String getLink() {
    return link;
  }
  
  public SpectacularRequisitionV2 ssn(String ssn) {
    this.ssn = ssn;
    return this;
  }

  
  /**
  * optional SSN field to verify ownership of the account
  * @return ssn
  **/
  @ApiModelProperty(value = "optional SSN field to verify ownership of the account")
  public String getSsn() {
    return ssn;
  }
  public void setSsn(String ssn) {
    this.ssn = ssn;
  }
  
  public SpectacularRequisitionV2 accountSelection(Boolean accountSelection) {
    this.accountSelection = accountSelection;
    return this;
  }

  
  /**
  * option to enable account selection view for the end user
  * @return accountSelection
  **/
  @ApiModelProperty(value = "option to enable account selection view for the end user")
  public Boolean isAccountSelection() {
    return accountSelection;
  }
  public void setAccountSelection(Boolean accountSelection) {
    this.accountSelection = accountSelection;
  }
  
  public SpectacularRequisitionV2 redirectImmediate(Boolean redirectImmediate) {
    this.redirectImmediate = redirectImmediate;
    return this;
  }

  
  /**
  * enable redirect back to the client after account list received
  * @return redirectImmediate
  **/
  @ApiModelProperty(value = "enable redirect back to the client after account list received")
  public Boolean isRedirectImmediate() {
    return redirectImmediate;
  }
  public void setRedirectImmediate(Boolean redirectImmediate) {
    this.redirectImmediate = redirectImmediate;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SpectacularRequisitionV2 spectacularRequisitionV2 = (SpectacularRequisitionV2) o;
    return Objects.equals(this.id, spectacularRequisitionV2.id) &&
        Objects.equals(this.created, spectacularRequisitionV2.created) &&
        Objects.equals(this.redirect, spectacularRequisitionV2.redirect) &&
        Objects.equals(this.status, spectacularRequisitionV2.status) &&
        Objects.equals(this.institutionId, spectacularRequisitionV2.institutionId) &&
        Objects.equals(this.agreement, spectacularRequisitionV2.agreement) &&
        Objects.equals(this.reference, spectacularRequisitionV2.reference) &&
        Objects.equals(this.accounts, spectacularRequisitionV2.accounts) &&
        Objects.equals(this.userLanguage, spectacularRequisitionV2.userLanguage) &&
        Objects.equals(this.link, spectacularRequisitionV2.link) &&
        Objects.equals(this.ssn, spectacularRequisitionV2.ssn) &&
        Objects.equals(this.accountSelection, spectacularRequisitionV2.accountSelection) &&
        Objects.equals(this.redirectImmediate, spectacularRequisitionV2.redirectImmediate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, created, redirect, status, institutionId, agreement, reference, accounts, userLanguage, link, ssn, accountSelection, redirectImmediate);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SpectacularRequisitionV2 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    redirect: ").append(toIndentedString(redirect)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    institutionId: ").append(toIndentedString(institutionId)).append("\n");
    sb.append("    agreement: ").append(toIndentedString(agreement)).append("\n");
    sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
    sb.append("    userLanguage: ").append(toIndentedString(userLanguage)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
    sb.append("    ssn: ").append(toIndentedString(ssn)).append("\n");
    sb.append("    accountSelection: ").append(toIndentedString(accountSelection)).append("\n");
    sb.append("    redirectImmediate: ").append(toIndentedString(redirectImmediate)).append("\n");
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


