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
