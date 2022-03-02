package com.group1.javaproject.players;

import com.group1.javaproject.deck.Deck;
import com.group1.javaproject.deck.UnoCard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An "AI" controlled player for the UnoGame class. Behaves similarly to <code>HumanPlayer</code>,
 * with the addition of playing random cards from their deck, instead of playing one based on user input
 *
 * @see HumanPlayer
 * @see UnoCard
 * @see Player
 * @author Julian Simmerman
 */
public class AiPlayer implements Player{
    private final String name;
    private final boolean isHuman = false;
    List<UnoCard> playerHand = new ArrayList<>();

    /**
     * Builds a new AI Player. Initial hand is dealt in creation
     * @param name Name of the AI player!
     */
    public AiPlayer(String name, int startingHand){
        this.name = name;
        playerHand.addAll(Deck.dealCards(startingHand));
    }

    /**
     * Draw cards from the deck to add to the current hand
     * @param amount Amount of cards to be added to the AI hand
     */
    @Override
    public void draw(int amount) {
        playerHand.addAll(Deck.drawCards(amount));
    }

    /**
     * This method will be used instead of the standard playCard method when it is an AI players turn.
     *
     * @return Returns the first valid card from the AI deck, if none are present returns null
     */
    @Override
    public UnoCard playCard() {
        //Gather a list of valid cards from the current hand
        List<UnoCard> validCards = playerHand.stream().filter(card -> isCardValid(card)).collect(Collectors.toList());

        if(validCards.size() == 0){
            //Must draw a card if no valid cards are present
            draw(1);

            //If a player draws, they don't play a card, and returns null to the game
            return null;
        }

        //create new reference for the card for readability
        UnoCard card = validCards.get(0);
        System.out.println(name + " played " + card);

        //card is removed once used
        playerHand.remove(card);

        //if the Ai has one card left, they automatically say uno
        if(playerHand.size() == 1){
            sayUno();
        }

        //if the chosen card is a wild card, pick a random color.
        if(card.getColor().equals("wild")){

            //This Ai doesn't care if they have that color in their hand or not
            card.setColor(getWildColor());

            System.out.println(name + " changed the color to " + card.getColor());
        }

        return card;
    }

    public String getWildColor(){
        double colorChoice = Math.random();
        if(colorChoice > 0.75){
           return "red";
        }else if(colorChoice > 0.5) {
            return "blue";
        }else if(colorChoice > 0.25){
            return "green";
        }
        return "yellow";
    }

    /**
     * When the AI player is down to their last card, they say Uno. This will always run with the last card,
     * no additional functionality needed.
     */
    @Override
    public void sayUno() {
        System.out.println("Uno!");
    }


    @Override
    public int checkCardCount(){
        return playerHand.size();
    }

    /**
     * Set the current to a provided set of cards. Method created for testing purposes
     * @param cards
     */
    public void setHand(List<UnoCard> cards) {
        //playerHand is final, can not use = to change contents to that of another collection
        //must first remove each UnoCard from playerHand, then add each new card to the List
        int size = playerHand.size();
        for (int i = 0; i < size; i++) {
            playerHand.remove(0);
        }
        playerHand.addAll(cards);
    }

    /**
     * The information on this player in a String format
     *
     * @return The printable version of the AI Player
     */
    @Override
    public String toString(){
        return "Ai Player:" +
                "name="+name+
                " number of cards="+checkCardCount();
    }

}
