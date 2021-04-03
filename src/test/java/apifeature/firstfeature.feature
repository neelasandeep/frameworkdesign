#Author: sandeep_neela@epam.com



Feature: First feature File
Scenario: get call test

Given when the user hits the reuest 'https://reqres.in/api/users/2' tovalidate 'scenario6'
When method GET
Then status 200

@posttag
Scenario: post request

Given user had 'https://reqres.in/api/users' to validate 'scenario1'
When user hits endpoint Post request
Then status 201
Scenario: get call test

Given when the user hits the reuest 'https://reqres.in/api/users/2' tovalidate 'scenario4'
When method GET
Then status 200

@posttag
Scenario: post request

Given user had 'https://reqres.in/api/users' to validate 'scenario2'
When user hits endpoint Post request
Then status 201
Scenario: get call test

Given when the user hits the reuest 'https://reqres.in/api/users/2' tovalidate 'scenario5'
When method GET
Then status 200

@posttag
Scenario: post request

Given user had 'https://reqres.in/api/users' to validate 'scenario3'
When user hits endpoint Post request
Then status 201