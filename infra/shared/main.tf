data "azurerm_client_config" "current" {}

module "resourcegroup" {
  source               = "./module_resourcegroup"
  application_name     = var.application_name
  environment_name     = var.environment_name
  environment_location = var.environment_location
  subscription_id      = data.azurerm_client_config.current.subscription_id
}

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

module "cloudflare" {
  source        = "./module_cloudflare"
  webapp_prefix = var.environment_name
  webapp_fqdn   = module.static_app.webapp_fqdn
  webapi_prefix = "api-${var.environment_name}"
  webapi_fqdn   = module.container_apps.webapi_fqdn
}

module "static_app" {
  source         = "./module_static_app"
  resource_group = module.resourcegroup.main

  custom_domain = "${var.environment_name}.fin2set.net"
}

module "github_repo" {
  source             = "./module_github_repo"
  webapi_fqdn        = module.container_apps.webapi_fqdn
  static_app_api_key = module.static_app.static_app_api_key
  environment_name   = var.environment_name
  CONTAINERAPP_NAME_WEBAPI = module.container_apps.containerapp_name
}

module "container_apps" {
  source                  = "./module_container_apps_webapi"
  resource_group          = module.resourcegroup.main
  log_analytics_workspace = module.log_analytics_workspace.main
  env = {
    # NORDIGEN_SECRET_ID  = module.keyvault.env.NORDIGEN_SECRET_ID
    # NORDIGEN_SECRET_KEY = module.keyvault.env.NORDIGEN_SECRET_KEY
    CR_PAT            = module.keyvault.env.CR_PAT
    GITHUB_USERNAME   = module.keyvault.env.GITHUB_USERNAME
    DATABASE_HOST     = module.database.database_host
    DATABASE_PORT     = module.database.database_port
    DATABASE_NAME     = module.database.database_name
    DATABASE_USERNAME = module.database.database_username
    DATABASE_PASSWORD = module.database.database_password
  }

}

module "log_analytics_workspace" {
  source         = "./module_log_analytics_workspace"
  resource_group = module.resourcegroup.main
}


module "database" {
  source           = "./module_database"
  resource_group   = module.resourcegroup.main
  admin_password   = module.keyvault.env.SQL_ADMIN_PASSWORD
  environment_name = var.environment_name
}
