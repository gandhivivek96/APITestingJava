Feature: Get all products from API

  Scenario: Verify successful retrieval of products
    Given the base API URL
    When I send a GET request to "/products"
    Then the response status code should be 200

  Scenario Outline: Verify the rate of first product
    Given the base API URL
    When I send a GET request to "/products"
    Then the rate of the first product in the response should be <FirstProductRate>

    Examples:
      | FirstProductRate |
      | 3.9              |
