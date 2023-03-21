Feature: test integration with account endpoints.

Scenario: test working get Account endpoint.

Given set accountNumberID from manually created requisition at sandbox.
When use endpoint account.
Then status code should be OK

Scenario: test working get Transactions endpoint.

Given set accountNumberID from manually created requisition at sandbox.
When use endpoint transaction.
Then status code should be OK