terraform {
  required_providers {

    # azuredevops = {
    #   source  = "microsoft/azuredevops"
    #   version = ">=0.2.0"
    # }

    azurerm = {
      source = "hashicorp/azurerm"
      version = ">=3.51.0"
    }

    azapi = {
      source = "Azure/azapi"
    }

  }
}

provider "azurerm" {

  # By default, Terraform will attempt to register any Resource Providers that it supports,
  # even if they're not used in your configurations to be able to display more helpful error messages.
  # If you're running in an environment with restricted permissions, or wish to manage
  # Resource Provider Registration outside of Terraform you may wish to disable this flag;
  # however, please note that the error messages returned from Azure may be confusing as a result
  # (example: API version 2019-01-01 was not found for Microsoft.Foo).
  # skip_provider_registration = true

  use_cli = false
  
  features {

    key_vault {

      # issue: when creating and destroying an environment, there is some competition between deleting access policy, and then of loosing access to read / delete secrets for e.g. SQL-ADMIN-PASSWORD secret.
      # solution from: https://stackoverflow.com/a/72506375/1237627
      recover_soft_deleted_key_vaults = false
      purge_soft_delete_on_destroy    = true

    }

  }
}

# provider "azuredevops" {
#   personal_access_token = var.onlex_sinnet_azdo_personal_token
#   org_service_url       = var.onlex_sinnet_azdo_service_url
# }
