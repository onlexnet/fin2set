output "reports_storage_address" {
  value = azurerm_storage_account.default.primary_blob_endpoint
}

output "reports_container_name" {
  value = azurerm_storage_container.reports.name
}

output "storage_account_name" {
  value = azurerm_storage_account.default.name
}

output "storage_account_website_url" {
  value = azurerm_storage_account.default.primary_web_endpoint
}
