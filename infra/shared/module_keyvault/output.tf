# in the future we would like to get such data directly from Azure Vault, but right now
# sharing them using terraform is the simplest option
output "env" {
  value = {
    # NORDIGEN_SECRET_ID = azurerm_key_vault_secret.NORDIGEN-SECRET-ID.value
    # NORDIGEN_SECRET_KEY = azurerm_key_vault_secret.NORDIGEN-SECRET-KEY.value
    GITHUB_USERNAME     = "siudeks"
    CR_PAT              = data.external.env.result["CR_PAT"],
    SQL_ADMIN_PASSWORD  = random_string.password.result,
    AUTH0_CLIENT_SECRET = data.external.env.result["AUTH0_CLIENT_SECRET"]
  }
  sensitive = true
}
