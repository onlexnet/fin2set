Feature: Change view

  Scenario: List of Customers
    When user1 current view is 'CHAT'
    And user user1 said "get full list of my customers"
    Then last user1 response is "Here's a list of your customers: 1. Client1, 2. Client2"
    And user1 current view is 'VIEW1'

  Scenario: Initial hints
    When user user1 said "What you can do for me"
    And last user1 response is "I can perform for you: 1. Analyze missing payments of your customers, 2. Manage your customer list"

  Scenario: Welcome hint
    Then for user1 initial message is "I can perform for you: 1. Analyze missing payments of your customers, 2. Manage your customer list"

