variable "onlexnet_finset_azure_dev01_subscription_id" {}

# impacts a lot of resources as a part of naming
# best practice: keep the same as folder name
variable "environment_name" {
    default = "dev01"
}


