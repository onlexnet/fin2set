variable "provider_token" {
  type = string
  sensitive = true
}

provider "fakewebservices" {
  token = var.provider_token
}
