Feature: Product API - Delete Product

  Scenario Outline: Delete a product with valid ID
    Given the base API URL
    When I send a DELETE request to <ProductID>
    Then the response status code should be 200
    Examples:
      | ProductID |
      | 6       |


