package com.group1.javaproject.players;

import com.group1.javaproject.UnoGame.UnoGame;
import com.group1.javaproject.deck.UnoCard;
import java.io.IOException;
import java.util.List;

/**
 * The player interface describes the behavior of each Player
 *
 * @see HumanPlayer
 * @see AiPlayer
 * @see UnoCard
 * @author Team Uno
 */
public interface Player {

    /**
     * Draw a card from the deck and add it to a player hand
     */
    void draw(int amount);

    /**
     * Play a card based on what the top card currently is.
     * @return a valid card to be returned to the game
     */
    UnoCard playCard() throws IOException;

    /**
     * If a player only has one card, they should yell UNO! or receive a penalty
     */
    void sayUno();


    /**
     * The total amount of cards a player has in their hand
     * @return the size of their hand
     */
    int checkCardCount();

    /**
     * Checks to see if the provided card is playable, based on the last card played
     * @param card the card to be checked for validity
     * @return true if card is valid, false if card can not be played
     */
    default boolean isCardValid(UnoCard card){
        //A more readable variable name
        UnoCard topCard = UnoGame.topCard;

        //the index the number is stored in for each UnoCard after being stylized
        int numberIndex = 10;

        //If the card is a wild card, or has the same number or color as the top card, it is a valid card
        return card.getColor().equals("wild") || card.getColor().equals(topCard.getColor()) ||
                card.getNumber().charAt(numberIndex) == topCard.getNumber().charAt(numberIndex); //char is primitive, and can be compared with ==
    }

    /**
     * Method created for testing purposes. Will set the current hand of this player to a new, provided hand
     * @param cards The cards to replace the current hand
     */
    void setHand(List<UnoCard> cards);

    void checkCardLength();

    String getName();

}
