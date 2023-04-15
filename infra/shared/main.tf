data "azurerm_client_config" "current" {}

data "azurerm_resource_group" "alldev" {
  name = "fin2set-env-alldev"
}

data "azurerm_container_registry" "alldev" {
  # provider            = azurerm.shared
  name                = "fin2setalldev"
  resource_group_name = data.azurerm_resource_group.alldev.name
}


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
  acr_admin_name = data.azurerm_container_registry.alldev.admin_username
  acr_admin_secret = data.azurerm_container_registry.alldev.admin_password
  acr_registry_url = data.azurerm_container_registry.alldev.login_server
}

module "container_apps" {
  source = "./module_container_apps"
  resource_group = module.resourcegroup.main
  log_analytics_workspace = module.log_analytics_workspace.main
  env = {
    NORDIGEN_SECRET_ID = module.keyvault.env.NORDIGEN_SECRET_ID
    NORDIGEN_SECRET_KEY = module.keyvault.env.NORDIGEN_SECRET_KEY
    DATABASE_HOST = module.database.database_host
    DATABASE_PORT = module.database.database_port
    DATABASE_NAME = module.database.database_name
    DATABASE_USERNAME = module.database.database_username
    DATABASE_PASSWORD = module.database.database_password
  }
    
}

module "log_analytics_workspace" {
  source = "./module_log_analytics_workspace"
  resource_group = module.resourcegroup.main
}


module "database" {
  source = "./module_database"
  resource_group = module.resourcegroup.main
  admin_password = module.keyvault.env.SQL_ADMIN_PASSWORD
  environment_name = var.environment_name
}
