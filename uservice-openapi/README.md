Link to schema api [nordigen](https://nordigen.com/docs/ais-schema.json)


## Cheat sheet for devs
(inspired by [Microsoft Learn](https://learn.microsoft.com/en-us/azure/container-registry/container-registry-java-quickstart#push-your-app-to-the-container-registry-via-jib))

### Address of OpenAPI
http://localhost:8080/swagger-ui/#


### To publish to Docker:
```bash
az acr login --name pocfin2set
mvnd clean install -DskipTests # to build binaries
mvnd compile jib:dockerBuild # to push image to local repository - should push to remote, but I don't know why it is in local
docker push pocfin2set.azurecr.io/fin2set:latest # push just created image to remove registry
```

## Useful links:
- https://openapi-generator.tech/docs/generators/java/
