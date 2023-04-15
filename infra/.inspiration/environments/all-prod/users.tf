data "azurerm_aadb2c_directory" "example" {
  resource_group_name = "sinnet-app-manual"
  domain_name         = "sinnetapp.onmicrosoft.com"
}

provider "azuread" {
  alias     = "sinnetapp"
  tenant_id = data.azurerm_aadb2c_directory.example.tenant_id
}

# Access to development
data "azuread_group" "developers" {
  display_name     = "SinNet Developer"
  security_enabled = true
}

# Access to production
data "azuread_group" "support" {
  display_name     = "SinNet Support"
  security_enabled = true
}


