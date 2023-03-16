Feature: Test integration with institution endpoints

    Scenario: Get list all institutions from poland and save random institution. Next check can you try find her using her id and check if the objects is the same.

        Given Get list all institutions from poland and save random institution.
        When Try find saved object.
        Then Check if the objects is the same.