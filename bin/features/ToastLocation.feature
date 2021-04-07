Feature: Unobstrusive Toast Location
  As a user, I wish my toast notifications to appear in the bottom left of the side bar.

  Scenario Outline: Creating and submitting new SWOT item
    Given a user is logged into the welcome page of Revature Staging Module
    When a user clicks Create SWOT for associate in table row "<rowId>"
    And selects their SWOT type "<SwotType>"
    And types into Enter Item field "<content>"
    And clicks add item
    And clicks submit
    Then a toast notification should appear in the lower left

    Examples:
      | rowId | SwotType    | content |
      | 1     | STRENGTH    | toast   |
      | 1     | WEAKNESS    | toast   |
      | 2     | OPPORTUNITY | toast   |
      | 2     | THREAT      | toast   |


  Scenario Outline: Deleting a submitted SWOT item
    Given a user is logged in and viewing a SWOT page for associate id"<associateId>"
    When a user clicks a SWOT delete button for "<itemID>"
    Then a toast notification should appear in the lower left

    Examples:
      | associateId | itemID |
      | 1           | 10     |
      | 1           | 11     |
      | 2           | 12     |
      | 2           | 13     |


  Scenario Outline: Updating a submitted SWOT item
    Given a user is logged in and viewing a SWOT page for associate id "<associateId>"
    When a user clicks a SWOT update button for "<itemID>"
    And clicks enter item field with text "<SwotContentOld>"
    And clears field of all data
    But adds text "<SwotContentNew>"
    And clicks Update SWOT
    Then a toast notification should appear in the lower left

    Examples:
      | associateId | itemID | SwotContentOld  | SwotContentNew |
      | 1           | 1      | Java            | new            |
      | 1           | 2      | Chocolate       | new            |
      | 2           | 3      | Soft skills     | new            |
      | 2           | 4      | Time management | new            |
