resource "azurerm_resource_group" "default" {
  name     = "${var.application_name}-env-${var.environment_name}"
  location = var.environment_location
}

data "azurerm_client_config" "current" {}

# Interesting case:
# if we skip such roles, current principal can't re-plan as it has lack of permission
# on key vault. I tried to add reader capabilities at key vault level, but id still fails.
# The solution was to add more roles dynamically as you can see below
resource azurerm_role_assignment rbac_assignment {
  scope                 = azurerm_resource_group.default.id
  role_definition_name  = "Key Vault Secrets User"
  principal_id          = data.azurerm_client_config.current.object_id
}
resource azurerm_role_assignment rbac_assignment2 {
  scope                 = azurerm_resource_group.default.id
  role_definition_name  = "Key Vault Secrets Officer"
  principal_id          = data.azurerm_client_config.current.object_id
}
resource azurerm_role_assignment rbac_assignment3 {
  scope                 = azurerm_resource_group.default.id
  role_definition_name  = "Key Vault Administrator"
  principal_id          = data.azurerm_client_config.current.object_id
}
