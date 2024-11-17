# chenile-bdd
BDD test cases for the chenile framework. This defines a Gherkin language and will be used for writing Integration test cases.
This is the equivalent of the Chenile cucumber-utils.

## Test case that demonstrates the BDD 
The test case that demonstrates the BDD (in folder it) requires two things to be running:
1. The Keycloak server in port 8180
2. The price server in port 8080

To run keycloak, use it/keycloak-start.sh script.
To run the price server, compile the price and priced modules. Run the priced server using "make run" in folder priced. 

## Dependencies
[REST-ASSURED](https://rest-assured.io/)
