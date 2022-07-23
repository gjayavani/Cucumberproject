@regression1
Feature: Login functionality
  Background:
    Given I am on the home page
    When I select sign in tab
    Then I should be on the Authentication page

  @smoke1
  #Happy path test case
  Scenario: verifying user can login successfully with valid credentials
    When I enter email as testaccount123@mailinator.com & password as Password123
    And I select sign in link
    Then I should be logged in successfully
    And I should be on My account page

   @smoke2
  #Unhappy path test case
    Scenario Outline: verifying user cannot login with invalid credentials & see the validation message
    When I enter email as <username> & password as <password>
    And I select sign in link
    Then I should be on the Authentication page
    And I should see the message as <validation_message>

    Examples:
         | username       | password     | validation_message         |
         | jaya.gmail.com | Password123  |  Invalid email address.    |
         | jaya@gmail.com |              |  Password is required.     |
      #   |                | Password123  |  An email address required.|
      #   | jaya@gmail.com | wefsdds      |  Authentication failed.    |
      #   |                |              |  An email address required.|
      #   | sdfsd@hmail.com| pass         |  Invalid password.         |
