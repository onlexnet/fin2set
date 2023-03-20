Feature: test integration with agreements endpoints

Scenario: create new agreement and check if his data is equals as example and delete him.

Given create new agreement.
When get created agreement.
Then check if the agreements is the same.
And delete agreement.

