resource "azurerm_resource_group" "default" {
  name     = "${local.application_name}-infra-prod"
  location = local.environment_location
}

resource "azurerm_dns_zone" "default" {
  # Currently the domain is delegated manually from Cloudflare
  # https://veducate.co.uk/dns-delegation-azure/
  name                = "fin2set.net"
  resource_group_name = azurerm_resource_group.default.name
}
