Feature: Change view

  Scenario: List of Customers
    When user1 current view is 'CHAT'
    And user user1 said "get list of my Customers"
    Then user1 current view is 'VIEW1'
    And last user1 response is "Here's a list of your customers: 1. Client1, 2. Client2"