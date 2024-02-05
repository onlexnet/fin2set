# UI part of fin2set

## Architecture decision record

### ADR-1 Component library
[Installing Fluent UI React v9](https://fluent2.microsoft.design/get-started/develop) from Microsoft.
- designed for mobiles
- data grids available in core library

### ADR-2 Deployment
As the application is currently available on [dev01 environment](dev01.fin2set.net)

### ADR-3 Schemas
- used OpenAPI spec
- used generator: https://github.com/ferdikoomen/openapi-typescript-codegen
- not tested alternatives:
  - https://github.com/drwpow/openapi-typescript
  - and more ....

## Used articles
- [Azure/static-web-apps-deploy@v1](https://learn.microsoft.com/en-us/azure/developer/javascript/how-to/with-authentication/static-web-app-with-api/deploy-static-web-app-to-azure#add-react-client-environment-variables-to-workflow-configuration-file)
- [Azure Static App configuration](https://learn.microsoft.com/en-us/azure/static-web-apps/configuration)
