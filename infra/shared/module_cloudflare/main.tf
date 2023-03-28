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

resource "cloudflare_record" "example" {
  zone_id = data.cloudflare_zone.fin2set.zone_id
  name    = var.host_name
  value   = var.default_host_name
  type    = "CNAME"
  ttl     = 300
}
