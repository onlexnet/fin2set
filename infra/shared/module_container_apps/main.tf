data "azurerm_container_registry" "alldev" {
  name                = "fin2setalldev"
  resource_group_name = "fin2set-env-alldev"
}


# Create user assigned identity and associated IAM role assignment
resource "azurerm_user_assigned_identity" "containerapp" {
  location            = var.resource_group.location
  name                = "containerappmi"
  resource_group_name = var.resource_group.name
}

resource "azurerm_role_assignment" "containerapp" {
  scope                = data.azurerm_container_registry.alldev.id
  role_definition_name = "acrpull"
  principal_id         = azurerm_user_assigned_identity.containerapp.principal_id
  depends_on = [
    azurerm_user_assigned_identity.containerapp
  ]
}

resource "azurerm_container_app_environment" "default" {
  name                       = "Example-Environment"
  location                   = var.resource_group.location
  resource_group_name        = var.resource_group.name
  log_analytics_workspace_id = var.log_analytics_workspace.id
}


# Unfortunatelly azurerm definition does not work
# https://github.com/hashicorp/terraform-provider-azurerm/issues/20435
# So please use definition in azapi
# resource "azurerm_container_app" "default" {
#   name                         = "dev01e"
#   container_app_environment_id = azurerm_container_app_environment.default.id
#   resource_group_name          = var.resource_group.name

#   // as per https://github.com/hashicorp/terraform-provider-azurerm/issues/20435#issuecomment-1437061107
#   revision_mode = "Multiple"

#   identity {
#     type         = "UserAssigned"
#     identity_ids = [azurerm_user_assigned_identity.containerapp.id]
#   }

#   registry {
#     server   = data.azurerm_container_registry.alldev.login_server
#     identity = azurerm_user_assigned_identity.containerapp.id
#   }

#   ingress {
#     external_enabled = true
#     target_port      = 8080
#     traffic_weight {
#       latest_revision = true
#       percentage      = 100
#     }
#   }

#   template {
#     container {
#       name = "examplecontainerapp"
#       # image  = "${data.azurerm_container_registry.alldev.login_server}/fin2set:latest"
#       image  = "busybox:latest"
#       cpu    = 0.25
#       memory = "0.5Gi"

#       # readiness_probe {
#       #   transport = "HTTP"
#       #   port      = 80
#       # }

#       # liveness_probe {
#       #   transport = "HTTP"
#       #   port      = 80
#       # }

#       # startup_probe {
#       #   transport = "HTTP"
#       #   port      = 80
#       # }

#     }
#   }
# }

resource "azapi_resource" "containerapp" {
  type      = "Microsoft.App/containerapps@2022-03-01"
  name      = "uservice-openapi"
  parent_id = var.resource_group.id
  location  = var.resource_group.location
 
 
  identity {
    type         = "SystemAssigned, UserAssigned"
    identity_ids = [azurerm_user_assigned_identity.containerapp.id]
  }
  body = jsonencode({
    properties = {
      # managedEnvironmentId = azapi_resource.containerapp_environment.id
      managedEnvironmentId = azurerm_container_app_environment.default.id
      configuration = {
        ingress = {
          external : true,
          targetPort : 80
        },
        "registries" : [
          {
            # "server" : data.azurerm_container_registry.acr.login_server,
            "server" : data.azurerm_container_registry.alldev.login_server
            "identity" : azurerm_user_assigned_identity.containerapp.id
          }
        ]
      }
      template = {
        containers = [
          {
            image = "${data.azurerm_container_registry.alldev.login_server}/fin2set:latest",
            name  = "firstcontainerappacracr"
            resources = {
              cpu    = 0.25
              memory = "0.5Gi"
            },
            "probes" : [
              {
                "type" : "Liveness",
                "httpGet" : {
                  "path" : "/",
                  "port" : 80,
                  "scheme" : "HTTP"
                },
                "periodSeconds" : 10
              },
              {
                "type" : "Readiness",
                "httpGet" : {
                  "path" : "/",
                  "port" : 80,
                  "scheme" : "HTTP"
                },
                "periodSeconds" : 10
              },
              {
                "type" : "Startup",
                "httpGet" : {
                  "path" : "/",
                  "port" : 80,
                  "scheme" : "HTTP"
                },
                "periodSeconds" : 10
              }
            ]
          }
        ]
        scale = {
          minReplicas = 0,
          maxReplicas = 2
        }
      }
    }
 
  })
  ignore_missing_property = true
  depends_on = [
    # azapi_resource.containerapp_environment
    azurerm_container_app_environment.default
  ]
}
