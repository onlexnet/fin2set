locals {
  application_name = "fin2set"
}

module "shared" {
  source                = "./shared"
  environment_name      = var.environment_name
  application_name      = local.application_name
}


