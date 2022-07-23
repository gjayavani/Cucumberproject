Feature: Shop and Order a product
 @smoke8
  Scenario: Verify user can able to search and order a product
    Given  I am on the home page
    When I enter the product name as Dresses in search box
    And  I click on search button
    When I select the product pink
    And I add the product to the cart
    Then I should see the product added to the cart
    When I select the Proceed to checkout button
    Then I should be on the SHOPPING cart page
    When I select the Proceed to checkout button on shopping page
    Then  I should be on the Authentication page
    When  I enter email as <email> & password as <password>
    Then  I should be logged in successfully
    And   I should be navigated to Addresses page to verify the billing & shipping addresses
    When  I select the Proceed to checkout
   # Then  I should be navigated to SHIPPING page
   # When  I should tick the checkbox to accept the terms & conditions
   # And   I select the Proceed to checkout tab
   # Then  I should be navigated to PAYMENT page to choose the payment method