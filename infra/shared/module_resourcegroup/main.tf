resource "azurerm_resource_group" "default" {
  name     = "${var.application_name}-env-${var.environment_name}"
  location = var.environment_location
}

data "azurerm_client_config" "current" {}

# Interesting case:
# if we skip such role, current principal can't re-plan as it has lack of permission
# on key vaule. I tried to add reader capabilities at key vault level, but id still fails.
# The solution was to add 'infra' as reader so oit can plan terraform changes, havinf whole knowledge about resources.
# So, to avoid limitation based on problem with planning because of lack of permission to get whole picture
# 'Reader' role seems to solve soch problems.
resource azurerm_role_assignment rbac_assignment {
  scope                 = azurerm_resource_group.default.id
  role_definition_name  = "Key Vault Secrets Officer"
  principal_id          = data.azurerm_client_config.current.object_id
}
