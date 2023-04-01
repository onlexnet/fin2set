Link to schema api [nordigen](https://nordigen.com/docs/ais-schema.json)

write sth

az config set defaults.acr=pocfin2set.azurecr.io
az acr login
mvnd clean install jib:dockerBuild -Dimage=sinnet.azurecr.io/uservice-webapi-host:latest -DskipTests


## Useful links:
- https://openapi-generator.tech/docs/generators/java/
