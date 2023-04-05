Feature: test integration with Bank endpoints

Scenario: get list all Banks from poland and save random Bank, next check can you go find her using her id and check if the objects is the same.

Given get list all Banks from poland and save random Bank.
When go find saved object.
Then check if the objects is the same.



