data "azurerm_resource_group" "prod" {
  name     = "${local.application_name}-infra-prod"
}

data "azurerm_dns_zone" "prod" {
  name                = "fin2set.net"
  resource_group_name = data.azurerm_resource_group.prod.name
}
