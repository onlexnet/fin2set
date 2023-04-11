# Configure the Cloudflare provider using the required_providers stanza
# required with Terraform 0.13 and beyond. You may optionally use version
# directive to prevent breaking changes occurring unannounced.
terraform {
  required_providers {
    cloudflare = {
      source  = "cloudflare/cloudflare"
      version = "~> 3.0"
    }
  }
}

data "cloudflare_zone" "fin2set" {
  name = "fin2set.net"
}

resource "cloudflare_record" "webapi" {
  zone_id = data.cloudflare_zone.fin2set.zone_id
  name    = var.webapi_prefix
  value   = var.webapi_fqdn
  type    = "CNAME"
  ttl     = 300
}

resource "cloudflare_record" "webapp" {
  zone_id = data.cloudflare_zone.fin2set.zone_id
  name    = var.webapp_prefix
  value   = var.webapp_fqdn
  type    = "CNAME"
  ttl     = 300
}
