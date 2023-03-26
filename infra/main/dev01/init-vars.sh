SUBSCRIPTION_NAME="onlexnet-fin2set-infra-dev"
export TENANT_ID=$(az account show --subscription $SUBSCRIPTION_NAME | jq -r .tenantId)

# fin2set-dns-edit is a token definition in CLoudflare allow to edit DNS entries in fin2set.net
# The token is manually hardcoded in Azure Vault to allow run provider from terraform to modify such DNS entries.
export APPLICATION_ID=$(az keyvault secret show --vault-name "sinnet-stg01" --name "application-id" | jq -r .value)

# az account set --subscription $SUBSCRIPTION_NAME
# export APPLICATION_ID=$(az keyvault secret show --vault-name "sinnet-stg01" --name "application-id" | jq -r .value)
# export OPERATOR1_NAME=$(az keyvault secret show --vault-name "sinnet-stg01" --name "test-operator-1-email" | jq -r .value)
# export OPERATOR1_PASSWORD=$(az keyvault secret show --vault-name "sinnet-stg01" --name "test-operator-1-password" | jq -r .value)
