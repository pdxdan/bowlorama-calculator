Feature: Calculate Score
  This is my poetic definition of a bowling score calculator

  Scenario: The score should match the value of a single roll
    When the player's only ball was a 5
    Then the score should be 5

  Scenario: The calculator should add an extra frame for a spare
    When the player bowls 10,1,2,3,4
    Then the score should be 23

  Scenario: Calculating a perfect score
    When the player bowls 10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10
    Then the score should be 300

