@regression3
Feature: Retrieving the forgotten password
   Background:
     Given I am on the home page
     When I select sign in tab
     Then I should be on the Authentication page
     When I click on Forgot password link in login panel
     Then  I should be navigated to password recovery page

  @smoke5
    #Happy path scenario
    Scenario: User can able to retrieve forgotten password
    When I enter an email testaccount123@mailinator.com to retrieve forgot password
    And  I click on Retrieve Password button
    Then I should see the confirmation message A confirmation email has been sent to your address:


  @smoke6
    #UnHappy path scenario
  Scenario: User can able to retrieve forgotten password
    When I enter an email jaya@yahoo.com to retrieve forgot password
    And  I click on Retrieve Password button
    Then I should see the message as There is no account registered for this email address.
