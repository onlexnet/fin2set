provider "azurerm" {

#  Error: Error ensuring Resource Providers are registered.
#  Terraform automatically attempts to register the Resource Providers it supports to
#  ensure it's able to provision resources.
#  If you don't have permission to register Resource Providers you may wish to use the
#  "skip_provider_registration" flag in the Provider block to disable this functionality.
  skip_provider_registration = true
  features {
  }
  
}
