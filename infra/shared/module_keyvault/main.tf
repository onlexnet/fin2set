data "azuread_group" "support" {
  display_name     = "${var.application_name}-${var.environment_name}-support"
  security_enabled = true
}

resource "random_pet" "pet" {
  keepers = {
    some_id = "100"
  }
  length = 2
}

data "azurerm_client_config" "current" {}

resource "azurerm_key_vault" "main" {
  name                       = "${var.application_name}-${random_pet.pet.id}"
  location                   = var.resourcegroup.location
  resource_group_name        = var.resourcegroup.name
  tenant_id                  = data.azurerm_client_config.current.tenant_id
  sku_name                   = "standard"
  enable_rbac_authorization  = true
  soft_delete_retention_days = 7

}

# resource "azurerm_key_vault_secret" "NORDIGEN-SECRET-ID" {
#   name         = "NORDIGEN-SECRET-ID"
#   value        = data.external.env.result["TF_VAR_NORDIGEN_SECRET_ID"]
#   key_vault_id = azurerm_key_vault.main.id
# }

# resource "azurerm_key_vault_secret" "NORDIGEN-SECRET-KEY" {
#   name         = "NORDIGEN-SECRET-KEY"
#   value        = data.external.env.result["TF_VAR_NORDIGEN_SECRET_KEY"]
#   key_vault_id = azurerm_key_vault.main.id
# }

# resource "azurerm_key_vault_secret" "OPENAI-KEY" {
#   name         = "OPENAI-KEY"
#   value        = data.external.env.result["OPENAI_KEY"]
#   key_vault_id = azurerm_key_vault.main.id
# }

# resource "azurerm_key_vault_secret" "OPENAI-ENDPOINT" {
#   name         = "OPENAI-ENDPOINT"
#   value        = data.external.env.result["OPENAI_ENDPOINT"]
#   key_vault_id = azurerm_key_vault.main.id
# }

resource "azurerm_key_vault_secret" "SQL-ADMIN-PASSWORD" {
  name         = "SQL-ADMIN-PASSWORD"
  value        = random_string.password.result
  key_vault_id = azurerm_key_vault.main.id
}

# Run the script to get the environment variables of interest.
# This is a data source, so it will run at plan time.
data "external" "env" {
  program = ["${path.module}/env.sh"]

  # For Windows (or Powershell core on MacOS and Linux),
  # run a Powershell script instead
  #program = ["${path.module}/env.ps1"]
}

resource "random_string" "password" {
  length  = 32
  special = true
}



