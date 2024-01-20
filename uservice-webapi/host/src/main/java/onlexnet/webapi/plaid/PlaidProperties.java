package onlexnet.webapi.plaid;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("fin2set.plaid")
record PlaidProperties(
    String plaidClientId,
    String plaidSecret) {}
