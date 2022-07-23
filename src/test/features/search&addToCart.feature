Feature: Search & add to cart functionality
  @smoke7
  Scenario: Verify user can able to search a product and add to the cart
    Given  I am on the home page
    When I enter the product name as Dresses in search box
    And  I click on search button
    When I select the product pink
    And I add the product to the cart
    Then I should see the product added to the cart
    When I select the Proceed to checkout tab

