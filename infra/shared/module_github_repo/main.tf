terraform {
  required_providers {
    github = {
      source  = "integrations/github"
      version = "~> 5.0"
    }
  }
}

data "github_repository" "current" {
  full_name = "onlexnet/fin2set"
}

resource "github_actions_secret" "DOCKER_USERNAME" {
  repository      = data.github_repository.current.name
  secret_name     = "DOCKER_USERNAME"
  plaintext_value = var.acr_admin_name
}

resource "github_actions_secret" "DOCKER_PASSWORD" {
  repository      = data.github_repository.current.name
  secret_name     = "DOCKER_PASSWORD"
  plaintext_value = var.acr_admin_secret
}

# resource "github_repository_environment" "dev01" {
#   environment = "example"
#   repository      = data.github_repository.current.name
#   # reviewers {
#   #   users = [data.github_user.current.id]
#   # }
#   # deployment_branch_policy {
#   #   protected_branches     = true
#   #   custom_branch_policies = false
#   # }
# }

resource "github_actions_variable" "DOCKER_REGISTRY_URL" {
  repository    = "fin2set"
  variable_name = "DOCKER_REGISTRY_URL"
  value         = var.acr_registry_url
}
