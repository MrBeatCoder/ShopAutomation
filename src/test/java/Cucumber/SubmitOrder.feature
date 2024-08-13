Feature: Purchase the order from E-Commerce Website

  Background:
    Given We land on the ECommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given We login with username <name> and password <password>
    When  We add the Product <productName> to Cart
    And   We Checkout <productName> and submit the order
    Then  "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    Examples:
      | name                  | password  | productName |
      | mrbeatcoder@gmail.com | Abcd1234! | ZARA COAT 3 |