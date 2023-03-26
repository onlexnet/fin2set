module "resourcegroup" {
  source               = "./module_resourcegroup"
  application_name     = var.application_name
  environment_name     = var.environment_name
  environment_location = var.environment_location
  subscription_id      = var.azure_subscription_id
}

module applications {
  source = "./module_applications"
}

# module "appinsights" {
#   source        = "./module_appinsights"
#   resourcegroup = module.resourcegroup.main
# }

# data "azurerm_container_registry" "sinnet" {
#   provider            = azurerm.shared
#   name                = "sinnet"
#   resource_group_name = "sinnet-default-manual"
# }

module "keyvault" {
  source           = "./module_keyvault"
  application_name = var.application_name
  environment_name = var.environment_name
  resourcegroup    = module.resourcegroup.main

  # appinsight_connection_string = module.appinsights.connection_string
  # support_security_group_name  = var.support_security_group
}

module "storage_account" {
  source           = "./module_storage_account"
  environment_name = var.environment_name
  resource_group   = module.resourcegroup.main
  application_name = var.application_name
}

module "b2c" {
  source = "./module_b2c"
  application_name = var.application_name
  resource_group = module.resourcegroup.main
}

# module "cloudflare" {
#   source = "./module_cloudflare"
# }
