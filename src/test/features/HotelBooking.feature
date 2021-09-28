Feature: As a user I want to be able to book a hotel and delete my booking on http://hotel-test.equalexperts.io/

  @regression @negative-scenario
  Scenario Outline: Make a hotel booking
    Given user launches the hotel booking website
    Then user is on hotel booking landing page
    And user can see details header for the booking
    When user enter new booking "<name>", "<surname>" and "<price>"
    And user select deposit option "<deposit>"
    And user select checkin and checkout date
    And user save booking details
    Then new booking details "<name>" are captured successfully

    Examples:
      | name        | surname | price | deposit |
      | Siphamandla | Sihlali | 20000 | false   |

  @regression @negative-scenario
  Scenario Outline: Remove hotel booking
    Given user launches the hotel booking website
    Then user is on hotel booking landing page
    And user can see details header for the booking
    When user delete the new booking "<name>"
    Then booking is deleted successfully "<name>"

    Examples:
      | name          |
      | Siphamandla   |


