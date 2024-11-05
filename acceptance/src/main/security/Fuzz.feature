Feature: Fuzz tests

  Scenario: Some random path
    Given request with some random path
    Then 404 Not Found response