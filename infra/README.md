# Goal
Infrastructre as code: creates environments and configure them
State location: Terraform Cloud

# Manual preparation
- Create account at hashicorp.com as we keep state in the HC cloud

# Start locally
```bash
terraform login # to connect to terraform cloud
```

### Prerequisites
* We need a single privileged service account to apply changes in Azure for all environments. It is not the best practice for large companies, where prod and non-prod should be separated, but it is very convinient for my local work and small clients. For such reason aad application named **onlex-infra** (single tenant application) has been created with permissions:
  - 'Contributor' role (to be able create resources) on each app subscription
  - 'Application administrator' to create service principals used in environments
  - with a secret named 'terraform-cli' (used to support CLI tool when required)


### Set prerequisit environment variables for local environment
All of variables required by developer to have access to remote resources are defined in Azure Value, so that - before using application - should be read using shell script to well-known environment variables, or read directly from Azure Vault

export TF_VAR_azure_subscription_id="........-....-....-....-............."



LAGACY TO REUSE BELOW:


set properly variables (for CI in pipeline, for CLI in local environment).
We suggest to create local - never commited - bash file in user home directory, where all required environment variables are defined. The file may be named 'onlex-sinnet-init.sh' with values:
```bash
export ARM_CLIENT_ID= ... client ID of onlex-infra # required by azurerm and azuread providers. Defines a principal able to create all resources
export ARM_CLIENT_SECRET= ... proper value from secrets mentioned above # required by azuread provider
export ARM_TENANT_ID= ... onlex.net tenant # Tenant where the resources are created. Required by azurerm and azuread providers
export ARM_SUBSCRIPTION_ID= ... OnLexNet subscription ID # required by backend provider

export TF_VAR_psql_infrauser_name="onlex_infra"
export TF_VAR_psql_infrauser_password= ... password of the infrauser
export TF_VAR_sinnet_k8s_host=localhost:1644 # remote server raport.sin.net.pl should be already linked to localhost using port redirection
export TF_VAR_sinnet_k8s_token= ... valid token stolen from k8s config from the host

export TF_VAR_sinnet_prod_subscription_id= ... onlexnet-sinnet-app-prd01 subscription ID
export TF_VAR_onlexnet_sinnet_dev01_subscription_id= ... onlexnet-sinnet-app-dev01 subscription ID
export TF_VAR_onlexnet_sinnet_stg01_subscription_id= ... onlexnet-sinnet-app-stg01 subscription ID
export TF_VAR_onlexnet_sinnet_prd01_subscription_id= ... onlexnet-sinnet-app-prd01 subscription ID

export TF_VAR_onlex_sinnet_azdo_personal_token="personal token"
export TF_VAR_onlex_sinnet_azdo_service_url="https://dev.azure.com/onlex"
```


### Work locally
* Assumption: use bash
* go to folder where you would like to apply changes (e.g. cd main/dev01/)
* **ssh -L 5432:localhost:5432 -L 16443:localhost:16443 <USERNAME>@raport.sin.net.pl** open session to unmanaged resources required by providers 
  * Now you may setup access to remote secured database as it is exposed as localhost:5432 thanks to ssh connection. Also remote microk8s is available at localhost:16443. Do not close thet session, keep it open till the end of your work with database and kubernetes (it means, in practive, terraform as well as it used those two resources)
* open new shell
**TODO . ~/onlex-sinnet-init.sh** set env variables 
* apply changes on selected env manually
  ```bash
  cd main/dev01
  terraform init # init your terraform once 
  terraform apply
  ```

### Used articles
- [How to add Docker registry secret to k8s](https://kubernetes.io/docs/concepts/configuration/secret/)
- [Az roles and permission matrix](https://www.azadvertizer.net/)
### Not used but promising articles
- https://stackoverflow.com/questions/64758735/terraform-cloud-failing-when-referencing-module-using-relative-local-path
- https://stackoverflow.com/questions/62137632/create-kubernetes-secret-for-docker-registry-terraform
- https://medium.com/citihub/a-more-secure-way-to-call-kubectl-from-terraform-1052adf37af8
- https://docs.microsoft.com/en-us/azure/key-vault/general/key-vault-integrate-kubernetes
- https://mrdevops.io/introducing-azure-key-vault-to-kubernetes-931f82364354
