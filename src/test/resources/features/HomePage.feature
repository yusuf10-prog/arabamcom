Feature: Arabam.com Homepage Functionality

  Scenario: User can visit homepage and accept cookies
    Given user is on the arabam.com homepage
    When cookies notification appears
    Then user accepts cookies

  Scenario: User can search for vehicles
    Given user is on the arabam.com homepage
    When user searches for "BMW"
    Then search results should be displayed
