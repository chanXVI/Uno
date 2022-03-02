package com.group1.javaproject.UnoGame;

/**
 * Methods that a game with turns would have.
 */
public interface HasTurns {
    /**
     * A players turn has been skipped
     */
    void skip();

    /**
     * The turn order is reversed
     */
    void reverse();

    /**
     * Determines the next turn
     */
    void nextTurn();

    /**
     * The start of a new turn
     */
    void startTurn();
}
