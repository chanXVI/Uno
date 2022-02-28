package com.group1.javaproject.players;

import com.group1.javaproject.UnoGame.UnoGame;
import com.group1.javaproject.deck.UnoCard;
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

    void draw();

    UnoCard playCard();

    void sayUno();

    void reverse();

    void skip();

    default int checkCardCount(){
        return playerHand.size();
    }

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
