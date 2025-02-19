Feature: Product API - Update Product

  Scenario Outline: Update a product with valid data
    Given the base API URL
    When I send a PUT request to <ProductID>
    Then the response status code should be 200
    Examples:
      | ProductID |
      | 9       |


