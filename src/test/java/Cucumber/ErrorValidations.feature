Feature: Error Validation

  @ErrorValidation
  Scenario Outline: Login Error Validation in Login Page
    Given We land on the ECommerce Page
    And We login with username <name> and password <password>
    Then  "Incorrect email or password." message is displayed
    Examples:
      | name                  | password   |
      | mrbeatcoder@gmail.com | Abcd12345! |