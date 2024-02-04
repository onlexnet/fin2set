Feature: Change view

  Scenario: List of customers
    When user user1 said "What is the weather in Boston, in Celcius"
    Then last user1 response is "The current weather in Boston is 35 degrees Celsius"
