package com.group1.javaproject.deck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * A holder class for our deck of cards. The contents of this class are immutable,
 * and the method calls exist to grab UnoCards from the list at random.
 *
 * @see UnoCard
 * @author
 */
public class Deck {
    // properties
    private static ArrayList<UnoCard> deck = new ArrayList<>();
    // constructors
    // methods

    /**
     * Deals the starting hand to a player
     *
     * @return A collection of random UnoCards
     */
    public static Collection<UnoCard> dealCards(int startingHandAmount){
        Collection<UnoCard> startingHand = new ArrayList<>();
            for(int cardsDealt = 0; cardsDealt < startingHandAmount; cardsDealt++){
                int index = (int)(Math.random() * deck.size());
                startingHand.add(deck.get(index));
            }
        return startingHand;
    }

    /**
     * Selects a number of cards at random from the deck, and returns them in a new collection
     * @param cardsToDraw Number of UnoCards to be added to the returned collection
     * @return A collection with a set amount of UnoCards
     */
    public static Collection<UnoCard> drawCards(int cardsToDraw){
        Collection<UnoCard> addToHand = new ArrayList<>();
        for(int cardDrawn = 0; cardDrawn < cardsToDraw; cardDrawn++){
            int index = (int)(Math.random() * deck.size());
            addToHand.add(deck.get(index));
        }
        return addToHand;
    }
    //toString

    // deck array list
    static {
        deck.add(new UnoCard("1", "blue"));
        deck.add(new UnoCard("2", "blue"));
        deck.add(new UnoCard("3", "blue"));
        deck.add(new UnoCard("4", "blue"));
        deck.add(new UnoCard("5", "blue"));
        deck.add(new UnoCard("6", "blue"));
        deck.add(new UnoCard("7", "blue"));
        deck.add(new UnoCard("8", "blue"));
        deck.add(new UnoCard("9", "blue"));
        deck.add(new UnoCard("1", "red"));
        deck.add(new UnoCard("2", "red"));
        deck.add(new UnoCard("3", "red"));
        deck.add(new UnoCard("4", "red"));
        deck.add(new UnoCard("5", "red"));
        deck.add(new UnoCard("6", "red"));
        deck.add(new UnoCard("7", "red"));
        deck.add(new UnoCard("8", "red"));
        deck.add(new UnoCard("9", "red"));
        deck.add(new UnoCard("1", "yellow"));
        deck.add(new UnoCard("2", "yellow"));
        deck.add(new UnoCard("3", "yellow"));
        deck.add(new UnoCard("4", "yellow"));
        deck.add(new UnoCard("5", "yellow"));
        deck.add(new UnoCard("6", "yellow"));
        deck.add(new UnoCard("7", "yellow"));
        deck.add(new UnoCard("8", "yellow"));
        deck.add(new UnoCard("9", "yellow"));
        deck.add(new UnoCard("1", "green"));
        deck.add(new UnoCard("2", "green"));
        deck.add(new UnoCard("3", "green"));
        deck.add(new UnoCard("4", "green"));
        deck.add(new UnoCard("5", "green"));
        deck.add(new UnoCard("6", "green"));
        deck.add(new UnoCard("7", "green"));
        deck.add(new UnoCard("8", "green"));
        deck.add(new UnoCard("9", "green"));
        deck.add(new UnoCard("+2", "wild"));
        deck.add(new UnoCard("+2", "wild"));
        deck.add(new UnoCard("+2", "wild"));
        deck.add(new UnoCard("+2", "wild"));
        deck.add(new UnoCard("wild+4", "wild"));
        deck.add(new UnoCard("wild+4", "wild"));
        deck.add(new UnoCard("wild+4", "wild"));
        deck.add(new UnoCard("wild+4", "wild"));
    }
}
