data "azurerm_client_config" "current" {}

module "resourcegroup" {
  source               = "./module_resourcegroup"
  application_name     = var.application_name
  environment_name     = var.environment_name
  environment_location = var.environment_location
  subscription_id      = data.azurerm_client_config.current.subscription_id
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

module "cloudflare" {
  source = "./module_cloudflare"
  webapp_prefix = var.environment_name
  webapp_fqdn = module.static_app.webapp_fqdn
  webapi_prefix = "api-${var.environment_name}"
  webapi_fqdn = module.container_apps.webapi_fqdn
}

module "static_app" {
  source = "./module_static_app"
  resource_group = module.resourcegroup.main
  
  custom_domain = "${var.environment_name}.fin2set.net"
}

module "github_repo" {
  source = "./module_github_repo"
  static_app_api_key = module.static_app.static_app_api_key
}

module "container_apps" {
  source = "./module_container_apps"
  resource_group = module.resourcegroup.main
  log_analytics_workspace = module.log_analytics_workspace.main
  env = module.keyvault.env
}

module "log_analytics_workspace" {
  source = "./module_log_analytics_workspace"
  resource_group = module.resourcegroup.main
}
