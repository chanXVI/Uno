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
   //
    public static final String BLACK_LETTERS = "\u001B[30m";
    public static final String BACKGROUND_COLOR_BLUE = "\u001B[44m";
    public static final String BACKGROUND_COLOR_RED = "\u001B[41m";
    public static final String BACKGROUND_COLOR_YELLOW = "\u001B[43m";
    public static final String BACKGROUND_COLOR_GREEN = "\u001B[42m";
    public static final String BACKGROUND_COLOR_WHITE = "\u001B[47m";

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
        deck.add(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"1", "blue"));
        deck.add(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"2", "blue"));
        deck.add(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"3", "blue"));
        deck.add(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"4", "blue"));
        deck.add(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"5", "blue"));
        deck.add(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"6", "blue"));
        deck.add(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"7", "blue"));
        deck.add(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"8", "blue"));
        deck.add(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"9", "blue"));
        deck.add(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS + "1", "red"));
        deck.add(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS + "2", "red"));
        deck.add(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS + "3", "red"));
        deck.add(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS + "4", "red"));
        deck.add(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS + "5", "red"));
        deck.add(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS + "6", "red"));
        deck.add(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS + "7", "red"));
        deck.add(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS + "8", "red"));
        deck.add(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS + "9", "red"));
        deck.add(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS + "1", "yellow"));
        deck.add(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS +"2", "yellow"));
        deck.add(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS +"3", "yellow"));
        deck.add(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS +"4", "yellow"));
        deck.add(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS +"5", "yellow"));
        deck.add(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS +"6", "yellow"));
        deck.add(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS +"7", "yellow"));
        deck.add(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS +"8", "yellow"));
        deck.add(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS +"9", "yellow"));
        deck.add(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS + "1", "green"));
        deck.add(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"2", "green"));
        deck.add(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"3", "green"));
        deck.add(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"4", "green"));
        deck.add(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"5", "green"));
        deck.add(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"6", "green"));
        deck.add(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"7", "green"));
        deck.add(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"8", "green"));
        deck.add(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"9", "green"));
        deck.add(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"+2", "wild"));
        deck.add(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS +"+2", "wild"));
        deck.add(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS +"+2", "wild"));
        deck.add(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"+2", "wild"));
        deck.add(new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS + "wild+4", "wild"));
        deck.add(new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS + "wild+4", "wild"));
        deck.add(new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS + "wild+4", "wild"));
        deck.add(new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS + "wild+4", "wild"));
    }
}
