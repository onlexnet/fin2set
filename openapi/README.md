# Notes

- We currently use Swagger 2!, as [Logic Apps are compatible with OpenAPI 2 only](https://learn.microsoft.com/en-us/connectors/custom-connectors/define-openapi-definition#import-the-openapi-definition)
- https://swagger.io/docs/specification/2-0/basic-structure/
- *schema.yaml* is intentionally in the root folder to simplify coding. Java part is just a part of compilation for schema-first approach.
- https://youtu.be/GJOwJrqIV-8

## Deployment
Manual: *mvnd clean package deploy*
