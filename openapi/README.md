# Notes

- We currently use Swagger 2!, as
  - [Logic Apps are compatible with OpenAPI 2 only](https://learn.microsoft.com/en-us/connectors/custom-connectors/define-openapi-definition#import-the-openapi-definition)
  - we already tested YAML format is not supported, so the definition should be kept in JSON format
  - side not: we use also corsproxy on applogic side as github exposed source files without CORS
- https://swagger.io/docs/specification/2-0/basic-structure/
- *schema.yaml* is intentionally in the root folder to simplify coding. Java part is just a part of compilation for schema-first approach.
- https://youtu.be/GJOwJrqIV-8
- [Working with the Apache Maven registry](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry)

## Deployment
Manual: *mvnd clean package deploy*
