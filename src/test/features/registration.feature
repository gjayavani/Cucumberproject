@regression2
Feature: Create a new account

  Background:
    Given I am on the home page
    When I select sign in tab
    Then I should be on the Authentication page

  @smoke3
  Scenario Outline: New user cannot able to create an account without providing mandatory fields
    When I enter an email as <email>
    And click on submit tab
    Then I should navigate to Create an account page
    And I should enter all the user details like title <title>,firstname <firstname>,lastname <lastname>,password <password>
    And I should enter Day <day>,Month <month>,Year <year>
    And I should enter address <address>,city <city>,state <state>,zip <zip>
    And I should enter mobile <mobile>
    When I should click on Register button
    Then I should see an error message <error>

    Examples:
     |     email       |title|firstname| lastname |password |day |month|year  | address    | city     |  state  |  zip  | mobile  |            error                           |
     | abcja@yahoo.com | Mr  | Jaya    | Vani     |pass123  |29  | 5   |2000  | Queen mary | Newyork  | Alaska  |       |         |You must register at least one phone number.|

  @smoke4
  Scenario Outline: New user cannot able to create an account without providing mandatory fields
    When I enter an email as <email>
    And click on submit tab
    Then I should navigate to Create an account page
    And I should enter all the user details like title <title>,firstname <firstname>,lastname <lastname>,password <password>
    And I should enter Day <day>,Month <month>,Year <year>
    #And I should enter address <address>,city <city>,state <state>,zip <zip>
    And I provide details as address,city,state,zip
     |Orlando|
     |Florida|
     |Alaska|
     |23456|
    And  I should enter mobile <mobile>
    When I should click on Register button
    Then I should see an error message <error>

  Examples:
    |     email       |title|firstname| lastname |password  |day |month|year  | mobile  |    error                                   |
    | vani@yahoo.com  | Mrs |   jaya  | vani     |pass123  |29  | 5   |2000   |         |You must register at least one phone number.|

