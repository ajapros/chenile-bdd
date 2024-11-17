Feature: Tests the price Service using a REST client.

  Scenario: Save the price without variant
    Given that "tenant" equals "tenant0"
    And that "employee" equals "E1"
    When I construct a REST request with header "x-chenile-tenant-id" and value "${tenant}"
    And I construct a REST request with header "x-chenile-eid" and value "${employee}"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I POST a REST request to URL "/price" with payload
    """
    {
      "id": ""
	}
	"""
    Then success is false
    And the http status code is 400
    And a REST exception is thrown with message "variantId field is required"

  Scenario: Save the price without amount
    Given that "tenant" equals "tenant0"
    And that "employee" equals "E1"
    When I construct a REST request with header "x-chenile-tenant-id" and value "${tenant}"
    And I construct a REST request with header "x-chenile-eid" and value "${employee}"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I POST a REST request to URL "/price" with payload
    """
    {
      "variantId": "Variant01"
	}
	"""
    Then success is false
    And the http status code is 400
    And a REST exception is thrown with message "amount field is required"

  Scenario: Save the price with negative amount
    Given that "tenant" equals "tenant0"
    And that "employee" equals "E1"
    When I construct a REST request with header "x-chenile-tenant-id" and value "${tenant}"
    And I construct a REST request with header "x-chenile-eid" and value "${employee}"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I POST a REST request to URL "/price" with payload
    """
    {
      "variantId": "Variant01",
      "amount": -10
	}
	"""
    Then success is false
    And the http status code is 400
    And a REST exception is thrown with message "amount should be greater than zero"

  Scenario: Save the price with zero amount
    Given that "tenant" equals "tenant0"
    And that "employee" equals "E1"
    When I construct a REST request with header "x-chenile-tenant-id" and value "${tenant}"
    And I construct a REST request with header "x-chenile-eid" and value "${employee}"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I POST a REST request to URL "/price" with payload
    """
    {
      "variantId": "Variant01",
      "amount": 0
	}
	"""
    Then success is false
    And the http status code is 400
    And a REST exception is thrown with message "amount should be greater than zero"

  Scenario: Save the price with non decimal amount
    Given that "tenant" equals "tenant0"
    And that "employee" equals "E1"
    When I construct a REST request with header "x-chenile-tenant-id" and value "${tenant}"
    And I construct a REST request with header "x-chenile-eid" and value "${employee}"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I POST a REST request to URL "/price" with payload
    """
    {
      "variantId": "Variant01",
      "amount": 100
	}
	"""
    Then success is true
    And the REST response key "variantId" is "Variant01"
    And the REST response key "amount" is "100.0"
    And store "$.payload.id" from  response to "id"
    And the REST response key "tenant" is "${tenant}"


  Scenario: Retrieve the saved price with non decimal amount
    Given that "entity" equals "price"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
    And I GET a REST request to URL "/${entity}/${id}"
    Then success is true
    And the REST response key "id" is "${id}"
    And the REST response key "variantId" is "Variant01"
    And the REST response key "amount" is "100.0"


  Scenario: Save the price with decimal amount
    Given that "tenant" equals "tenant0"
    And that "employee" equals "E1"
    When I construct a REST request with header "x-chenile-tenant-id" and value "${tenant}"
    And I construct a REST request with header "x-chenile-eid" and value "${employee}"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I POST a REST request to URL "/price" with payload
    """
    {
      "variantId": "Variant02",
      "amount": 100.19
	}
	"""
    Then success is true
    And the REST response key "variantId" is "Variant02"
    And the REST response key "amount" is "100.19"
    And store "$.payload.id" from  response to "id"
    And the REST response key "tenant" is "${tenant}"


  Scenario: Retrieve the saved non decimal price
    Given that "entity" equals "price"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
    And I GET a REST request to URL "/${entity}/${id}"
    Then success is true
    And the REST response key "id" is "${id}"
    And the REST response key "variantId" is "Variant02"
    And the REST response key "amount" is "100.19"


  Scenario: Save the price with long decimal amount
    Given that "tenant" equals "tenant0"
    And that "employee" equals "E1"
    When I construct a REST request with header "x-chenile-tenant-id" and value "${tenant}"
    And I construct a REST request with header "x-chenile-eid" and value "${employee}"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I POST a REST request to URL "/price" with payload
    """
    {
      "variantId": "Variant03",
      "amount": 200.111111
	}
	"""
    Then success is true
    And the REST response key "variantId" is "Variant03"
    And the REST response key "amount" is "200.11"
    And store "$.payload.id" from  response to "id"
    And the REST response key "tenant" is "${tenant}"


  Scenario: Retrieve the Price with long decimal amount
    Given that "entity" equals "price"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
    And I GET a REST request to URL "/${entity}/${id}"
    Then success is true
    And the REST response key "id" is "${id}"
    And the REST response key "variantId" is "Variant03"
    And the REST response key "amount" is "200.11"

  Scenario: Retrieve the Price with random id
    Given that "entity" equals "price"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
    And I GET a REST request to URL "/${entity}/price_01"
    Then success is false
    And the http status code is 404
    And a REST exception is thrown with message "Unable to find price with ID price_01"

  Scenario: Save the duplicate price for variant
    Given that "tenant" equals "tenant0"
    And that "employee" equals "E1"
    When I construct a REST request with header "x-chenile-tenant-id" and value "${tenant}"
    And I construct a REST request with header "x-chenile-eid" and value "${employee}"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I POST a REST request to URL "/price" with payload
    """
    {
      "variantId": "Variant03",
      "amount": 200.111111
	}
	"""
    Then success is false
    And the http status code is 400
    And a REST exception is thrown with message "Price record already exists for variant"

  Scenario: Delete the price for a variant
    Given that "tenant" equals "tenant0"
    And that "employee" equals "E1"
    When I construct a REST request with header "x-chenile-tenant-id" and value "${tenant}"
    And I construct a REST request with header "x-chenile-eid" and value "${employee}"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I DELETE a REST request to URL "/price/${id}" with payload
    """
    {
    }
    """
    Then success is true
    And the http status code is 200