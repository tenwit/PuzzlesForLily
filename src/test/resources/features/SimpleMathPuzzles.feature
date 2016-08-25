Feature: Simple Math Puzzles

  Scenario: Lily wants to do maths
    When Lily requests a new puzzle
    Then a simple puzzle is presented to Lily

  Scenario: A simple puzzle is being presented
    When a simple puzzle is presented to Lily
    Then an equation is generated
    And one number or operation is obfuscated
    And the equation is presented

  Scenario: Lily correctly answers a puzzle
    Given a simple puzzle is presented to Lily
    When Lily enters the correct answer
    Then A congratulatory message is presented to her

  Scenario: Lily incorrectly answers a puzzle
    Given a simple puzzle is presented to Lily
    When Lily enters the incorrect answer
    Then An encouraging message is presented to her
