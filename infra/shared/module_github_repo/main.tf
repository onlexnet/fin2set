data "azurerm_client_config" "current" {}
data "azuread_client_config" "current" {}
data "github_repository" "current" {
  full_name = "onlexnet/fin2set"
}

# Currently we use the same principal for deployment as for terraform
resource "azuread_service_principal_password" "current" {
  end_date             = "2299-12-30T23:00:00Z" # Forever
  service_principal_id = data.azuread_client_config.current.object_id
  # value                = "${random_string.password.result}"
}


terraform {
  required_providers {
    github = {
      source  = "integrations/github"
      version = "~> 5.0"
    }
  }
}


resource "github_actions_secret" "WEBAPP_KEY_API" {
  repository      = data.github_repository.current.name
  secret_name     = "WEBAPP_KEY_API"
  plaintext_value = var.static_app_api_key
}

# resource "github_actions_secret" "DOCKER_USERNAME" {
#   repository      = data.github_repository.current.name
#   secret_name     = "DOCKER_USERNAME"
#   # plaintext_value = var.acr_admin_name
# }

# resource "github_actions_secret" "DOCKER_PASSWORD" {
#   repository      = data.github_repository.current.name
#   secret_name     = "DOCKER_PASSWORD"
#   plaintext_value = var.acr_admin_secret
# }

resource "github_repository_environment" "main" {
  environment = var.environment_name
  repository  = data.github_repository.current.name
  # reviewers {
  #   users = [data.github_user.current.id]
  # }
  # deployment_branch_policy {
  #   protected_branches     = true
  #   custom_branch_policies = false
  # }
}

resource "github_actions_environment_variable" "webapi_fqdn" {
  value         = var.webapi_fqdn
  variable_name = "webapi_fqdn"
  environment   = github_repository_environment.main.environment
  repository    = data.github_repository.current.name
}


resource "github_actions_environment_secret" "fin2set_client_id" {
  environment     = github_repository_environment.main.environment
  repository      = data.github_repository.current.name
  secret_name     = "FIN2SET_CLIENT_ID"
  plaintext_value = data.azurerm_client_config.current.client_id
}

resource "github_actions_environment_secret" "fin2set_secret_id" {
  environment     = github_repository_environment.main.environment
  repository      = data.github_repository.current.name
  secret_name     = "FIN2SET_CLIENT_SECRET"
  plaintext_value = resource.azuread_service_principal_password.current.value
}

# resource "github_actions_secret" "AZURE_CLIENT_ID" {

#   repository      = data.github_repository.current.name
#   secret_name     = "AZURE_CLIENT_ID"
#   plaintext_value = data.azurerm_client_config.current.client_id
# }

# resource "github_actions_secret" "AZURE_CLIENT_SECRET" {
#   repository      = data.github_repository.current.name
#   secret_name     = "AZURE_CLIENT_SECRET"
#   plaintext_value = resource.azuread_service_principal_password.current.value
# }

resource "github_actions_secret" "AZURE_SUBSCRIPTION_ID" {
  repository      = data.github_repository.current.name
  secret_name     = "AZURE_SUBSCRIPTION_ID"
  plaintext_value = data.azurerm_client_config.current.subscription_id
}

resource "github_actions_secret" "AZURE_TENANT_ID" {
  repository      = data.github_repository.current.name
  secret_name     = "AZURE_TENANT_ID"
  plaintext_value = data.azurerm_client_config.current.tenant_id
}

resource "github_actions_environment_variable" "CONTAINERAPP_NAME_WEBAPI" {
  environment   = github_repository_environment.main.environment
  repository    = data.github_repository.current.name
  variable_name = "CONTAINERAPP_NAME_WEBAPI"
  value         = var.CONTAINERAPP_NAME_WEBAPI
}
