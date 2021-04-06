Feature: Toast Message
	As a user I wish to interact with the page without first clicking on the message
	
	Scenario Outline: Interacting with the page with active message
	Given User is at the login page
	When user inputs their email "<email>"
	And user inputs their password "<password>"
	And user clicks log in
	And user clicks create swot
	And user inputs swot title "<title>"
	And user inputs swot item name "<item>"
	And user selects swot type "<typeone>"
	And user inputs swot note "<note>"
	And user clicks add item
	And user clicks view swots
	And user clicks add new item
	And user selects type "<typetwo>"
	And user submits item name "<text>"
	And user inputs note "<notetwo>"
	And user clicks add item
	Then user can click add new item again without clearing the message
	
	Examples:
	| email             | password | title | item  | typeone  | note | typetwo     | text           | notetwo |
	| test@revature.com | password | test  | test  | Strength | test | Opportunity | just checking  | testtwo |
	| test@revature.com | password | test  | test  | Strength | test | Opportunity | checking again | testtwo |
	| test@revature.com | password | test  | test  | Strength | test | Opportunity | last time      | testtwo |
	
	Scenario Outline: Ensuring toasts are clearly visible
	Given User is at the login page
	When user inputs their email "<email>"
	And user inputs their password "<password>"
	And user clicks log in
	And user clicks create swot
	And user inputs swot title "<title>"
	And user inputs swot item name "<item>"
	And user selects swot type "<typeone>"
	And user inputs swot note "<note>"
	And user clicks add item
	And user clicks view swots
	And user clicks add new item
	And user selects type "<type>"
	And user submits item name "<text>"
	And user inputs note "<notetwo>"
	And user clicks add item
	And user clicks update for recently created item
	And user changes item type "<typetwo>"
	And user clicks update swot button
	And user clicks update for recently created item
	And user clicks delete for the same item
	Then the three messages should occupy separate locations on the screen
	
	Examples:
	| email             | password | title | item  | typeone  | note | type        | text           | notetwo | typetwo  |
	| test@revature.com | password | test  | test  | Strength | test | Opportunity | just checking  | testtwo | Strength |
	| test@revature.com | password | test  | test  | Strength | test | Opportunity | checking again | testtwo | Strength |
	| test@revature.com | password | test  | test  | Strength | test | Opportunity | last time      | testtwo | Strength |
	
	Scenario Outline: Test ensuring page contains no javascript alerts
	Given User is at the login page
	When user inputs their email "<email>"
	And user inputs their password "<password>"
	And user clicks log in
	And user clicks create swot
	And user inputs swot title "<title>"
	And user inputs swot item name "<item>"
	And user selects swot type "<typeone>"
	And user inputs swot note "<note>"
	And user clicks add item
	And user clicks view swots
	And user clicks add new item
	And user selects type two "<typetwo>"
	And user submits item name three "<texttwo>"
	And user inputs note "<notetwo>"
	And user clicks add item
	And user clicks update for recently created item
	And user changes item type "<typethree>"
	And user clicks update swot button
	And user clicks update for recently created item
	And user clicks delete for the same item
	Then the user should have four toast messages
	
	Examples:
	| email             | password | title | item  | typeone  | note | typetwo  | texttwo     | notetwo | typethree |
	| test@revature.com | password | test  | test  | Strength | test | Weakness | more checks | testtwo | Strength  |
	| test@revature.com | password | test  | test  | Strength | test | Weakness | testing     | testtwo | Strength  |
	| test@revature.com | password | test  | test  | Strength | test | Weakness | than sorry  | testtwo | Strength  |
	
	