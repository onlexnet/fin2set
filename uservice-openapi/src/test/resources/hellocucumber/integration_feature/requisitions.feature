Feature: test integration with requisition endpoints.

Scenario: create a agreement and use it to create an requisition, then just find us the requisition and check if the result id matches
the one you created, then delete the requisition and see if you can find it with its id and verify that the agreement with the
deleted object has been deleted.

Given create new agreement for requisition.
And create new requisition.
When looking created requisition based at id.
And check if created requisition have the same id as found.
And delete created requisition.
Then looking deleted requisition.
And looking if agreement was deleted with requisition.