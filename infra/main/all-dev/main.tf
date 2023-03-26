data "azurerm_client_config" "current" {}

resource "azurerm_resource_group" "default" {
  name     = "${local.application_name}-infra-dev"
  location = local.environment_location
}

