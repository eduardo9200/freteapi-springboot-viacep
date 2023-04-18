Feature: Search of cep number

  Scenario: Call backend with cep number
    When the client calls endpoint "/frete"
    Then response status code is 200
    And returned string
    