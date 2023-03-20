Feature: test integration with institution endpoints

Scenario: get list all institutions from poland and save random institution, next check can you go find her using her id and check if the objects is the same.

Given get list all institutions from poland and save random institution.
When go find saved object.
Then check if the objects is the same.



