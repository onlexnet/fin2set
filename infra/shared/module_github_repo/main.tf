terraform {
  required_providers {
    github = {
      source  = "integrations/github"
      version = "~> 5.0"
    }
  }
}

data "github_actions_public_key" "example_public_key" {
  repository = "fin2set"
}

resource "github_actions_secret" "example_secret" {
  repository       = "fin2set"
  secret_name      = "WEBAPP_KEY_API"
  plaintext_value  = var.static_app_api_key
}
