# Dev notes

- Link to schema api [nordigen](https://nordigen.com/docs/ais-schema.json)
- [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=onlexnet_fin2set&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=onlexnet_fin2set)


## Cheat sheet for devs
(inspired by [Microsoft Learn](https://learn.microsoft.com/en-us/azure/container-registry/container-registry-java-quickstart#push-your-app-to-the-container-registry-via-jib))

### Address of OpenAPI
http://localhost:8080/swagger-ui/index.html


### To publish to Docker:
```bash
az acr login --name fin2setalldev
mvnd clean install -DskipTests # to build binaries
mvnd compile jib:dockerBuild # to build locally java-based image, and, alternatively ...
mvnd -Pnative spring-boot:build-image -DskipTests # to build locally native java-based image
docker push fin2setalldev.azurecr.io/fin2set:latest # push just created java image to remove registry
docker push fin2setalldev.azurecr.io/fin2set-native:latest # push just created native image to remove registry
```

### To run Sonar from CLI

## Useful links:
- https://openapi-generator.tech/docs/generators/java/
- Native java
  - https://www.baeldung.com/spring-native-intro#1-maven
