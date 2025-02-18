Feature: Product API - Create Product

  Scenario Outline: Create a new product with valid data
    Given the base API URL
    When I send a POST request to "/products"
    And I pass the request body of product title "<ProductTitle>"
    Then the response status code should be 200
    Examples:
      | ProductTitle |
      | Mobile       |