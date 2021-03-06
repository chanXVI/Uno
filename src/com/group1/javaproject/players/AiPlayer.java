package com.group1.javaproject.players;

import com.group1.javaproject.deck.Deck;
import com.group1.javaproject.deck.UnoCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
       checkCardLength();
    }

    /**
     * This method will be used instead of the standard playCard method when it is an AI players turn.
     *
     * @return Returns the first valid card from the AI deck, if none are present returns null
     */
    @Override
    public UnoCard playCard() {
        /**
         * Gather a list of valid cards from the current hand.
         */
        List<UnoCard> validCards = playerHand.stream().filter(this::isCardValid).collect(Collectors.toList());

        /**
         * AI player waits for a second before either drawing or playing a card
         */
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("============================");

        if(validCards.size() == 0){
            /**
             * Must draw a card if no valid cards are present
             */
            System.out.println(name + " has no playable cards and has to draw.");
            draw(1);

            /**
             * If a player draws, they don't play a card, and returns null to the game
             * @return null
             */
            return null;
        }

        /**
         * Create new reference for the card for readability.
         */
        UnoCard card = validCards.get(0);
        System.out.println(name + " played " + card);

        /**
         * Card is removed once used
         */
        playerHand.remove(card);

        /**
         * If the Ai has one card left, they automatically say uno
         */
        if(playerHand.size() == 1){
            sayUno();
        }

        /**
         * If the chosen card is a wild card, pick a random color.
         */
        if(card.getColor().equals("wild")){

            /**
             * This Ai doesn't care if they have that color in their hand or not.
             */
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
     * @param cards the cards to replace the current hand with
     */
    public void setHand(List<UnoCard> cards) {
        /**
         * playerHand is final, can not use = to change contents to that of another collection.
         * Must first remove each UnoCard from playerHand, then add each new card to the List.
         */
        //
        int size = playerHand.size();
        if (size > 0) {
            playerHand.subList(0, size).clear();
        }
        playerHand.addAll(cards);
    }

    @Override
    public void checkCardLength(){
        while(checkCardCount() > 20){
            int randomPoint = new Random().nextInt(checkCardCount()) + 1;
            playerHand.remove(randomPoint - 1);
        }
    }
    public String getName(){
        return name.toUpperCase();
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
