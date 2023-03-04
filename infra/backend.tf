# The block below configures Terraform to use the 'remote' backend with Terraform Cloud.
# For more information, see https://www.terraform.io/docs/backends/types/remote.html
terraform {
  backend "remote" {
    # Well-known manually created organization name, managed by site app.terraform.io.
    organization = "onlexnet"

    workspaces {
      name = "dev01"
    }
  }

  required_version = ">= 0.14.0"
}

module "resourcegroup" {
  source               = "./module_resourcegroup"
  application_name     = locals.application_name
  environment_name     = var.environment_name
  environment_location = var.environment_location
  subscription_id      = var.env_subscription_id
}

locals {
  application_name = "FinSet"
}
