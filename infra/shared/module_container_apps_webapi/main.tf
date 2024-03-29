# data "azurerm_container_registry" "alldev" {
#   name                = "fin2setalldev"
#   resource_group_name = "fin2set-env-alldev"
# }


# Create user assigned identity and associated IAM role assignment
resource "azurerm_user_assigned_identity" "containerapp" {
  location            = var.resource_group.location
  name                = "containerappmi"
  resource_group_name = var.resource_group.name
}

# resource "azurerm_role_assignment" "containerapp" {
#   scope                = data.azurerm_container_registry.alldev.id
#   role_definition_name = "acrpull"
#   principal_id         = azurerm_user_assigned_identity.containerapp.principal_id
#   depends_on = [
#     azurerm_user_assigned_identity.containerapp
#   ]
# }

resource "azurerm_container_app_environment" "default" {
  name                       = "dev01-env"
  location                   = var.resource_group.location
  resource_group_name        = var.resource_group.name
  log_analytics_workspace_id = var.log_analytics_workspace.id
}

# Known issues:f
# https://github.com/hashicorp/terraform-provider-azurerm/issues/20435
resource "azurerm_container_app" "default" {
  name                         = "uservice-webapi"
  container_app_environment_id = azurerm_container_app_environment.default.id
  resource_group_name          = var.resource_group.name
  revision_mode                = "Single"

  identity {
    type         = "UserAssigned"
    identity_ids = [azurerm_user_assigned_identity.containerapp.id]
  }

  registry {
    server               = "ghcr.io"
    username             = var.env.GITHUB_USERNAME
    password_secret_name = "cr-pat"
  }

  # step 2
  ingress {
    external_enabled = true
    target_port      = 8080
    traffic_weight {
      latest_revision = true
      percentage      = 100
    }
  }

  secret {
    name  = "cr-pat"
    value = var.env.CR_PAT
  }

  #  the secret has been aded automatuically by manual integration of container app with oauth provider
  secret {
    name = "auth0-authentication-secret"
    value = var.env.AUTH0_CLIENT_SECRET
  }

  secret {
    name  = "database-host"
    value = var.env.DATABASE_HOST
  }

  secret {
    name  = "database-port"
    value = var.env.DATABASE_PORT
  }

  secret {
    name  = "database-name"
    value = var.env.DATABASE_NAME
  }

  secret {
    name  = "database-password"
    value = var.env.DATABASE_PASSWORD
  }

  secret {
    name  = "database-username"
    value = var.env.DATABASE_USERNAME
  }

  dapr {
    app_id       = "uservice-webapi"
    app_port     = "8080"
    app_protocol = "http"
  }

  lifecycle {
    ignore_changes = [template[0].container[0].image]
  }

  template {

    min_replicas = 1
    max_replicas = 1

    container {
      name   = "uservice-webapi"
      image  = "ghcr.io/onlexnet/fin2set-webapi:latest"
      cpu    = 0.5
      memory = "1Gi"

      env {
        name        = "DATABASE_HOST"
        secret_name = "database-host"
      }

      env {
        name        = "DATABASE_PORT"
        secret_name = "database-port"
      }

      env {
        name        = "DATABASE_NAME"
        secret_name = "database-name"
      }

      env {
        name        = "DATABASE_USERNAME"
        secret_name = "database-username"
      }

      env {
        name        = "DATABASE_PASSWORD"
        secret_name = "database-password"
      }

      env {
        name  = "SPRING_PROFILES_ACTIVE"
        value = "prod"
      }

      # readiness_probe {
      #   transport = "HTTP"
      #   port      = 80
      # }

      # liveness_probe {
      #   transport = "HTTP"
      #   port      = 80
      # }

      # startup_probe {
      #   transport = "HTTP"
      #   port      = 80
      # }

    }
  }
}

# resource "azapi_resource" "containerapp" {
#   type      = "Microsoft.App/containerapps@2022-03-01"
#   name      = "uservice-webapi-native"
#   parent_id = var.resource_group.id
#   location  = var.resource_group.location


#   identity {
#     type         = "UserAssigned"
#     identity_ids = [azurerm_user_assigned_identity.containerapp.id]
#   }

#   body = jsonencode({
#     properties = {
#       # managedEnvironmentId = azapi_resource.containerapp_environment.id
#       managedEnvironmentId = azurerm_container_app_environment.default.id
#       configuration = {
#         ingress = {
#           external : true,
#           targetPort : 8080
#         },
#         registries : [
#           {
#             "server" : data.azurerm_container_registry.alldev.login_server
#             "identity" : azurerm_user_assigned_identity.containerapp.id
#           }
#         ]
#       }
#       template = {
#         containers = [
#           {
#             # step1 - use any publicitry available image
#             # image = "busybox:latest"
#             # step2 - use target image
#             image = "${data.azurerm_container_registry.alldev.login_server}/fin2set-native:latest",
#             name  = "firstcontainerappacracr"
#             resources = {
#               cpu    = 0.25
#               memory = "0.5Gi"
#             },
#             env = [
#             ]
#             # "probes" : [
#             #   {
#             #     "type" : "Liveness",
#             #     "httpGet" : {
#             #       "path" : "/",
#             #       "port" : 80,
#             #       "scheme" : "HTTP"
#             #     },
#             #     "periodSeconds" : 10
#             #   },
#             #   {
#             #     "type" : "Readiness",
#             #     "httpGet" : {
#             #       "path" : "/",
#             #       "port" : 80,
#             #       "scheme" : "HTTP"
#             #     },
#             #     "periodSeconds" : 10
#             #   },
#             #   {
#             #     "type" : "Startup",
#             #     "httpGet" : {
#             #       "path" : "/",
#             #       "port" : 80,
#             #       "scheme" : "HTTP"
#             #     },
#             #     "periodSeconds" : 10
#             #   }
#             # ]
#           }
#         ]
#         scale = {
#           minReplicas = 0,
#           maxReplicas = 2
#         }
#       }
#     }

#   })
#   ignore_missing_property = true
#   depends_on = [
#     # azapi_resource.containerapp_environment
#     azurerm_container_app_environment.default
#   ]
# }
