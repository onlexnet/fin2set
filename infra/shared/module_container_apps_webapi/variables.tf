variable "resource_group" {
  type = object({
    id       = string
    location = string
    name     = string
  })
}

variable "log_analytics_workspace" {
  type = object({
    id = string
  })
}

variable env {
  type = object({
    CR_PAT = string
    GITHUB_USERNAME = string
    DATABASE_HOST = string
    DATABASE_NAME = string
    DATABASE_PORT = string
    DATABASE_USERNAME = string
    DATABASE_PASSWORD = string
    AUTH0_CLIENT_SECRET = string
  })
}
