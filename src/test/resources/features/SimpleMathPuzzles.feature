Feature: Simple Math Puzzles

  Scenario: Lily wants to do maths
    When Lily requests a new puzzle
    Then a simple puzzle is presented to Lily
    And the puzzle is an obfuscated equation
    And Lily is prompted for an answer

  Scenario: Lily correctly answers a puzzle
    Given the game is waiting for an answer from Lily
    When Lily enters the correct answer
    Then A congratulatory message is presented to Lily

  Scenario: Lily incorrectly answers a puzzle
    Given the game is waiting for an answer from Lily
    When Lily enters the incorrect answer
    Then An encouraging message is presented to Lily
