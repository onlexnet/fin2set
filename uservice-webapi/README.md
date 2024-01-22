# Backend part of frontend.

## To run locally
- mvn spring-boot:run -pl -am host

## GraphQL
- to communicate with React UI. Console available http://localhost:8080/graphiql

## REST
- to expose extensions for OpenAI. Console available http://localhost:8080/ (or http://localhost:8080/swagger-ui/index.html)

## Test secrets locally
- run DAPR with secrets
- view in browser loaded secrets
```
dapr run --resources-path=components --dapr-http-port 3601
curl http://localhost:3601/v1.0/secrets/azurekeyvault/OPENAI-KEY
```

## Hints
To communicate with service principal we need local certification file. If we do not have, or is invalid, we need to generate it:
```
# read tenant id. It needs manual operation using web browser
export TENANT_ID=$(az login --use-device-code | jq -r '.[] | select(.isDefault == true) | .tenantId')

# read APP_ID based on application display name
export APP_ID=$(az ad app list --display-name=onlexnet-infra-fin2set | jq -r '.[] | .appId')

# save the values for future share with DAP container
echo "{\"APP_ID\":\"$APP_ID\", \"TENANT_ID\":\"$TENANT_ID\"}" > .secrets/tenant_and_app.json
```

### Create certificate to sign on as Service Principal
```
# create local Certificate Signing Request
openssl req -newkey rsa:4096 -sha512  -days 365 -nodes -out .secrets/cert.crs -keyout .secrets/service-principal.key
# create local Certificate
openssl x509 -days 365 -req -signkey .secrets/service-principal.key -in .secrets/cert.crs -out .secrets/service-principal.crt
# combine files together
cat .secrets/service-principal.crt .secrets/service-principal.key > .secrets/service-principal.pem

# add generated cert to service principal
az ad app credential reset --id $APP_ID --append --cert @.secrets/service-principal.pem --display-name cert-self

# optional: see the cert imported
az ad app credential list --id $APP_ID --cert

# test if you can login using the certificate
az login --service-principal -u $APP_ID -p .secrets/service-principal.pem --tenant $TENANT_ID
```

### Create certificate to sign on as Service Principal (alternative way)
instead of creation of your certificate, Azure may create certificate for you, just consider to use
```
az ad app credential reset --id -u $APP_ID --append --create-cert --display-name cert-auto
```

### Random notes/hints
- check available updates:
  ```
  mvn versions:display-property-updates -ntp -pl host
  ```

### Used articles
- https://www.baeldung.com/spring-boot-environmentpostprocessor
- https://www.youtube.com/watch?v=We0ISk2jGCw
- https://learn.microsoft.com/en-us/cli/azure/azure-cli-sp-tutorial-3
- https://docs.spring.io/spring-ai/reference/concepts.html
- https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/openai/azure-ai-openai/README.md
