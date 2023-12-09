# Goal
Infrastructre as code: creates environments and configure them
State location: Terraform Cloud

## Manual preparation
- Create account at hashicorp.com as we keep state in the HC cloud

### Prerequisites
The infrastructure is currently designed to be started by manual invocation by a person with proper permissions. 

To run Terraform providers, some pre-configuration is required:
#### Cloudflare
- created token with EDIT permission for fin2set.net zone.
- the token is named fin2set-dns-edit (what doesn't matter for Terraform, is here ust to reference)
- the token value is used as sensitive named variable in Terraform Cloud, names [CLOUDFLARE_API_TOKEN](https://registry.terraform.io/providers/cloudflare/cloudflare/latest/docs)

#### GitHub
- created token named fin2set-secrets, scoped to fin2set repo, with permissions: Administration (read/write), Environments (read/write), Secrets (read/write), Variables (read/write)
- the token value is used as sensitive variable Terraform Cloud, named [GITHUB_TOKEN](https://registry.terraform.io/providers/integrations/github/latest/docs#oauth--personal-access-token)
- for some reason I had to set also variable GITHUB_OWNER=onlexnet as (without that) github tried to construct invalid path mixed with organization and my username
- also, to read github images, we use additional token (named CR_PAT as *Container Registry Personal Access Token*) to pull docker images

#### Azure AD
- created service principal named **onlexnet-infra-fin2set** (single tenant application) has been created with permissions:
  - 'Contributor' role (to be able create resources) on each app subscription
  - 'Application administrator' to create service principals used in environments
  - 'Azure B2C Contributor' custom role with permissions required to manager B2C directories: Microsoft.AzureActiveDirectory/b2cDirectories/* where * is read,write and delete 
  - 'User Access Administrator' (on selected, used subscription) to add RBAC roles to objects
  - with a secret named e.g. 'terraform-cli' (used to support CLI tool)
- sensitive environment variables named ARM_CLIENT_ID, ARM_CLIENT_SECRET and ARM_TENANT_ID are set properly [as required](https://registry.terraform.io/providers/hashicorp/azuread/latest/docs/guides/service_principal_client_secret#environment-variables)

#### Azure RM
- we use the same service principal as mentioned above *Azure AD*
- it uses the same variables as above ...
- ... plus one more variable: [ARM_SUBSCRIPTION_ID](https://registry.terraform.io/providers/hashicorp/azurerm/latest/docs/guides/service_principal_client_secret#configuring-the-service-principal-in-terraform)

