Feature: Descriptive Toast Text
  As a user, I wish my toast notifications to specify what I've changed.

  Scenario Outline: Creating and submitting new SWOT item
    Given a user is logged into the welcome page of Revature Staging Module
    When a user clicks Create SWOT for associate "<assoiateName>"
    And clicks Select SWOT type
    And selects their "<SwotType>"
    And types into Enter Item field "<content>"
    And clicks submit
    Then a toast notification should appear
    But the toast header should say 'New SWOT Created' with id: "<id>"
    And the toast text should say "<SwotType>" for "<associateName>"

    Examples:
      | testA | Java            | STRENGTH    |
      | testA | Chocolate       | WEAKNESS    |
      | testB | Soft skills     | OPPORTUNITY |
      | testB | Task management | THREATS     |


  Scenario Outline: Deleting a submitted SWOT item
    Given a user is logged in and viewing a SWOT page for associate id "<associateId>"
    When a user clicks a SWOT delete button for "<itemId>"
    Then a toast notification should appear
    But the toast header should say 'SWOT Deleted' with id: "<itemId>"
    And the toast text should say "<SwotType>" for "<associateName>"

    Examples:
      | associateId | itemId | SwotType    | associateName |
      | 1           | 10     | STRENGTH    | testA         |
      | 1           | 11     | WEAKNESS    | testA         |
      | 2           | 12     | OPPORTUNITY | testB         |
      | 2           | 13     | WEAKNESS    | testB         |


  Scenario Outline: Updating a submitted SWOT item's SWOT Type
    Given a user is logged in and viewing a SWOT page for associate id "<associateId>"
    When a user clicks a SWOT update button for "<itemId>"
    And clicks SWOT dropdown with type "<SwotTypeOld>"
    And selects option "<SwotTypeNew>"
    And clicks Update SWOT
    Then a toast notification should appear
    But the toast header should say 'SWOT Updated' with id: "<itemId>"
    And the toast text should say "<SwotTypeNew>" for "<associateName>"

    Examples:
      | associateId | itemId | SwotTypeOld | SwotTypeNew | associateName |
      | 1           | 1      | STRENGTH    | THREATS     | testA         |
      | 1           | 2      | WEAKNESS    | OPPORTUNITY | testA         |
      | 2           | 3      | OPPORTUNTIY | WEAKNESS    | testB         |
      | 2           | 4      | THREATS     | STRENGTHS   | testB         |


  Scenario Outline: Updating a submitted SWOT item's Content
    Given a user is logged in and viewing a SWOT page for associate id "<associateId>"
    When a user clicks a SWOT update button for "<itemId>"
    And clicks enter item field with text "<SwotContentOld>"
    And clears field of all data
    But replaces text with "<SwotcontentNew>"
    And clicks Update SWOT
    Then a toast notification should appear
    But the toast header should say 'SWOT Updated' with id: "<itemId>"
    And the toast text should say "<SwotTypeNew>" for "<associateName>"

    Examples:
      | associateId | itemId | SwotContentOld  | SwotcontentNew   | associateName |
      | 1           | 1      | Java            | C#               | testA         |
      | 1           | 2      | Chocolate       | Calorie Counting | testA         |
      | 2           | 3      | Soft skills     | Hard skills      | testB         |
      | 2           | 4      | Time management | Multitasking     | testB         |