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
    NORDIGEN_SECRET_ID = string
    NORDIGEN_SECRET_KEY = string
  })
}
