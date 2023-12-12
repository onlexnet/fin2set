Feature: Change view

  Scenario: The example
    When user1 current view is 'CHAT'
    And user user1 said "show my clients"
    Then user1 current view is 'VIEW1'
