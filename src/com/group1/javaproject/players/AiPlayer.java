package com.group1.javaproject.players;

import com.group1.javaproject.deck.Deck;
import com.group1.javaproject.deck.UnoCard;

import java.util.List;
import java.util.stream.Collectors;

/**
 * An "AI" controlled player for the UnoGame class. Behaves similarly to <code>HumanPlayer</code>
 * with the addition of playing random cards from their deck, instead of playing one based on user input
 *
 * @see HumanPlayer
 * @author Julian Simmerman
 */
public class AiPlayer implements Player{
    private String name;
    private boolean isHuman = false;

    public AiPlayer(String name){
        this.name = name;
        playerHand.addAll(Deck.dealCards());
    }

    /**
     * Draw a card from the deck to add to the current hand
     */
    @Override
    public void draw() {
        playerHand.add(Deck.drawCard());
    }

    /**
     * This method will be used instead of the standard playCard method when it is an AI players turn.
     *
     * @return A random valid card from the AI deck, if none are present returns null
     */
    @Override
    public UnoCard playCard() {
        //Gather a list of valid cards from the current hand
        List<UnoCard> validCards = playerHand.stream().filter(card -> isCardValid(card)).collect(Collectors.toList());

        if(validCards.size() == 0){
            //Must draw a card if no valid cards are present
            draw();

            //If a player draws, they don't play a card
            return null;
        }

        //Returning the first index for now, for testing purposes
        return validCards.get(0);
    }

    /**
     * When the AI player is down to their last card, they say Uno
     *
     * TODO: add additional functionality with
     */
    @Override
    public void sayUno() {
        System.out.println("Uno!");
    }

    /**
     * Reverse the order of turns
     * TODO: implementation. Implement while implementing turns
     */
    @Override
    public void reverse() {

    }

    /**
     * Skip the player turn
     * TODO: implementation. Implement while implementing turns
     */
    @Override
    public void skip() {

    }
}
