resource "azurerm_dns_zone" "default" {
  # Currently the domain is delegated manually from Cloudflare
  # https://veducate.co.uk/dns-delegation-azure/
  name                = "dev.fin2set.net"
  resource_group_name = azurerm_resource_group.default.name
}

resource "azurerm_dns_ns_record" "example" {
  name                = "dev"
  zone_name           = data.azurerm_dns_zone.prod.name
  resource_group_name = data.azurerm_dns_zone.prod.resource_group_name
  ttl                 = 300

  records = azurerm_dns_zone.default.name_servers

}
