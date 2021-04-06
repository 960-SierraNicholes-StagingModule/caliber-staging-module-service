Feature: Create SWOT

    Scenario Outline: As a manager, I wish to create a SWOT analysis for an associate with a descriptive name
    Given manager is at the login page
    When manager inputs their email "<email>"
    And manager inputs their password "<password>"
    And manager clicks log in
    Then manager is redirected to home page
    Given manager is at home page
    And manager clicks Create Swot 
    And manager inputs content "<content>"
    And manager selects type strength
    And manager clicks Add Item
    And manager inputs description "<description>"
    And manager clicks Submit Swot
    And manager clicks ok in window alert
    And manager clicks View Swots
    Then manager is redirect to view swots page
    And manager can view created SWOT

    Examples:
 	| email             | password | content | description |
    | test@revature.com | password | Java    | Associate 1 |