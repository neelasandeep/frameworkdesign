#Author: sandeep_neela@epam.com
Feature: First feature File

  Scenario: get call test
    Given when the user had the 'apidata.json'
    When method GET with 'https://reqres.in/api/users/2' to validate 'Scenario'
    Then status 200

  @posttag
  Scenario: post request
    Given when the user had the 'apidata.json'
    When user hits endpoint Post request 'https://reqres.in/api/users' to validate 'scenario1'
    Then status 201
    Given when the user had the 'apidata.json'
    When method GET with 'https://reqres.in/api/users/2' to validate 'Scenario2'
    Then status 200

  @posttag
  Scenario: post request
    Given when the user had the 'apidata.json'
    When user hits endpoint Post request 'https://reqres.in/api/users' to validate 'scenario3'
    Then status 201

  Scenario: get call test
    Given when the user had the 'apidata.json'
    When method GET with 'https://reqres.in/api/users/2' to validate 'Scenario4'
    Then status 200

  @posttag
  Scenario: post request
    Given when the user had the 'apidata.json'
    When user hits endpoint Post request 'https://reqres.in/api/users' to validate 'scenario5'
    Then status 201
    