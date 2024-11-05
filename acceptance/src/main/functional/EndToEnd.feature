Feature: End to end acceptance test of the API

  Scenario: No message
    Given hello request
    Then 200 OK response
    And message is "Hello World"

  Scenario: Some message
    Given hello request with message "Jack"
    Then 200 OK response
    And message is "Hello Jack"
