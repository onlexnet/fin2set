resource "azurerm_aadb2c_directory" "tenant" {
  country_code            = "PL"
  data_residency_location = "Europe"
  display_name            = var.application_name
  domain_name             = "${var.application_name}.onmicrosoft.com"
  resource_group_name     = var.resource_group.name
  sku_name                = "PremiumP1"
}
