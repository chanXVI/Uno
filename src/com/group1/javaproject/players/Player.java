package com.group1.javaproject.players;

import com.group1.javaproject.UnoGame.UnoGame;
import com.group1.javaproject.deck.Deck;
import com.group1.javaproject.deck.UnoCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The player interface describes the behavior of each Player
 *
 * @see HumanPlayer
 * @see AiPlayer
 * @author Team Uno
 */
public interface Player {

    List<UnoCard> playerHand = new ArrayList<>();

    /**
     * Draw a card from the deck and add it to a player hand
     */
    void draw();

    /**
     * Play a card based on what the top card currently is.
     * @return a valid card to be returned to the game
     */
    UnoCard playCard();
    UnoCard playCard() throws IOException;

    /**
     * If a player only has one card, they should yell UNO! or recieve a penalty
     */
    void sayUno();

    /**
     * Reverse the order of turns
     */
    void reverse();

    /**
     * Skip the next player
     */
    void skip();

    /**
     * The total amount of cards a player has in their hand
     * @return the size of their hand
     */
    default int checkCardCount(){
        return playerHand.size();
    }

    /**
     * Checks to see if the provided card is playable, based on the last card played
     * @param card the card to be checked for validity
     * @return true if card is valid, false if card can not be played
     */
    default boolean isCardValid(UnoCard card){
        //A more readable variable name
        UnoCard topCard = UnoGame.topCard;

        //If the card is a wild card, or has the same number or color as the top card, it is a valid card
        if(card.getColor() == "wild"  || card.getColor() == topCard.getColor() || card.getNumber() == topCard.getNumber()){
            return true;
        }

        //return false if card is not valid
        return false;
    }


    /**
     * Set the current to a provided set of cards. Method created for testing purposes
     * @param cards
     */
    default void setHand(List<UnoCard> cards){
        //playerHand is final, can not use = to change contents to that of another collection
        //must first remove each UnoCard from playerHand, then add each new card to the List
        int size = playerHand.size();
        for(int i = 0; i < size; i++){
            playerHand.remove(0);
        }
        playerHand.addAll(cards);
    }
}
