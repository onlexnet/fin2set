Feature: test integration with account endpoints.

Scenario: test working get Account endpoint.

Given set accountNumberID from manually created requisition at sandbox.
When use endpoint account.
Then the ID of the found object should match the set one.

Scenario: test working get Transactions endpoint.

Given set accountNumberID from manually created requisition at sandbox.
When use endpoint transaction.
Then status code should be OK.