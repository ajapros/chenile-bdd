Feature: Tests the price Service using a REST client.
 
  Scenario: Save the price first.
    Given that "tenant" equals "tenant0"
    And that "employee" equals "E1"
    When I construct a REST request with header "x-chenile-tenant-id" and value "${tenant}"
    And I construct a REST request with header "x-chenile-eid" and value "${employee}"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I POST a REST request to URL "/price" with payload
    """
    {
      "variantId": "variant1",
      "amount": 101.00
	}
	"""
	Then success is true
    And store "$.payload.id" from  response to "id"
    # And the REST response key "tenant" is "${tenant}"
    # And the REST response key "createdBy" is "${employee}"

  Scenario: Retrieve the saved price
    Given that "entity" equals "price"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
    And I GET a REST request to URL "/${entity}/${id}"
    Then success is true
    And the REST response key "id" is "${id}"

  Scenario: Save a price using an ID that already is determined
  Given that "id" equals "123"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
    And I construct a REST request with header "x-chenile-eid" and value "E1"
    And I POST a REST request to URL "/price" with payload
  """
  {
    "id": "${id}",
     "variantId": "variant2",
     "amount": 102.00
  }
  """
    Then success is true
    And the REST response key "id" is "${id}"

  Scenario: Retrieve the saved price
  Given that "entity" equals "price"
    When I construct a REST request with authorization header in realm "tenant0" for user "t0-premium" and password "t0-premium"
    And I construct a REST request with header "x-chenile-tenant-id" and value "tenant0"
    And I GET a REST request to URL "/${entity}/${id}"
    Then success is true
    And the REST response key "id" is "${id}"


    