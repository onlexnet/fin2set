# Backend part of frontend.

## To run locally
- mvn spring-boot:run -pl -am host

## GraphQL
- to communicate with React UI

## REST
- to expose extensions for OpenAI

## Test secrets locally
- run DAPR with secrets
- view in browser loaded secrets
```
dapr run --resources-path=components --dapr-http-port 3601
curl http://localhost:3601/v1.0/secrets/azurekeyvault/OPENAI-KEY
```

## Interesting links
- https://docs.spring.io/spring-ai/reference/concepts.html
- https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/openai/azure-ai-openai/README.md
