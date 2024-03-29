# The block below configures Terraform to use the 'remote' backend with Terraform Cloud.
# For more information, see https://www.terraform.io/docs/backends/types/remote.html
terraform {

  backend "remote" {
    # Well-known manually created organization name, managed by site app.terraform.io.
    organization = "onlexnet"

    workspaces {
      name = "fin2set-dev01"
    }
  }

  # backend "local" {
  #   path = "terraform.tfstate"
  # }

  required_version = ">= 0.14.0"
}
