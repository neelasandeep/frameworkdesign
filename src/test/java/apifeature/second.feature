#Author: sandeep_neela@epam.com



Feature: First feature File
Scenario: get call test

Given when the user hits the reuest 'https://reqres.in/api/users/2' tovalidate 'scenario12'
When method GET
Then status 200

@posttag
Scenario: post request

Given user had 'https://reqres.in/api/users' to validate 'scenario7'
When user hits endpoint Post request
Then status 201
Scenario: get call test

Given when the user hits the reuest 'https://reqres.in/api/users/2' tovalidate 'scenario8'
When method GET
Then status 200

@posttag
Scenario: post request

Given user had 'https://reqres.in/api/users' to validate 'scenario9'
When user hits endpoint Post request
Then status 201
Scenario: get call test

Given when the user hits the reuest 'https://reqres.in/api/users/2' tovalidate 'scenario10'
When method GET
Then status 200

@posttag
Scenario: post request

Given user had 'https://reqres.in/api/users' to validate 'scenario11'
When user hits endpoint Post request
Then status 201