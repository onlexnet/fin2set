Feature: test integration with token endpoints

  Scenario: get access and refresh token and use refresh token to get
            new access token, check if the token you get is not equal to the old token 

    Given get access token.
    When use refresh token to get new access token, and set refreshed access token.
    Then first access token will be not equals refreshed token.
