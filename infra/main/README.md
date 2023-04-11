# Dev notes

Because of instability of many Terraform providers, sometimes some operations should be done in [more that one step](https://github.com/hashicorp/terraform-provider-azurerm/issues/20435), so when you see environment definition with multiple steps, apply them one by one. Such environments name will be .e.g dev01-step0, dev01-step1 etc.
