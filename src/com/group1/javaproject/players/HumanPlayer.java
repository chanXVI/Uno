package com.group1.javaproject.players;

import com.group1.javaproject.deck.Deck;
import com.group1.javaproject.deck.UnoCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.group1.javaproject.UnoGame.UnoGame.lastCardPlayed;
import static com.group1.javaproject.UnoGame.UnoGame.topCard;

/**
 * A human controlled player for the UnoGame class. Each turn, a method is called and the user will be
 * prompted to play a card from their hand.
 *
 * @see AiPlayer
 * @see UnoCard
 * @author Charles Williams
 */
public class HumanPlayer implements Player{

    private final String name;
    List<UnoCard> playerHand = new ArrayList<>();

    /**
     * Creation of a new Human Player. Initial hand is dealt in creation
     * @param name Name of our player!
     */

    public HumanPlayer(String name){
        this.name = name;
//        playerHand.addAll(Deck.dealCards(10));
    }

    public HumanPlayer(String name, int startingHand){
        this(name);
        playerHand.addAll(Deck.dealCards(startingHand));
    }

    /**
     * Draws a number of cards from the deck to add to the player hand
     * @param amount Amount of cards to be added to the player hand
     */
    @Override
    public void draw(int amount) {
        playerHand.addAll(Deck.drawCards(amount));
        checkCardLength();
    }

    /**
     * For a human player to play a card, they must be asked which card they want to play.
     * The selected card should be playable based on the last card that was played.
     * If they don't have any cards they can play, they should draw a card and return null
     *
     * @return The card being played
     * @throws IOException
     */
    @Override
    public UnoCard playCard()  {
        //System.out.println(topCard);

        System.out.println(topCard.getColor().toUpperCase() + " " + topCard.getNumber() + " is currently on top of pile");
        System.out.println("============================");
        //see hand before picking card
        int y = 1;
        for(UnoCard card : playerHand){
            System.out.println(y + "." + card);
            y++;
        }
//        System.out.println(playerHand);


        //choice card that you want to play
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        System.out.println("What card does " + name +" want to play?");

        String card = null; //enter place in arraylist to get element
        try {
            card = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //entered number will be changed to int
        int x = Integer.parseInt(card) - 1;

        //if the number 'x' is larger than size of player hand. Show message and try again

        if (x > playerHand.size()){
            System.out.println(x + " SLOT EMPTY. Please pick playable card");
            playCard();
        }

        //getting card that you picked from list
        UnoCard pickedCard = playerHand.get(x);

        //if card NOT a valid card, draw, and end turn. if card IS valid show card picked, and remove from hand
        if (!isCardValid(pickedCard)){
            System.out.println(pickedCard + " is not a valid card please draw"); //trying to play an invalid card

            InputStreamReader drawInput = new InputStreamReader(System.in);
            BufferedReader drawReader = new BufferedReader(drawInput);
            System.out.println("press '0' to draw a card or any other number to try again");
            String drawCard = null;

            try {
                drawCard = drawReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int d = Integer.parseInt(drawCard);
            if (d == 0){
                draw(1);
                System.out.println("One card has been drawn" + playerHand);
            } else{
                playCard();
            }

            return null;
        } else{
            System.out.println("You picked: " + pickedCard); //int x used to get UnoCard from "index x" of playerHand
            playerHand.remove(x); //remove from hand
        }

        if (pickedCard.getColor().equalsIgnoreCase("wild")){
            InputStreamReader colorInput = new InputStreamReader(System.in);
            BufferedReader colorReader = new BufferedReader(colorInput);
            System.out.println(name + " please pick a color: RED, YELLOW, BLUE, GREEN");
            String choice = null;
            try {
                choice = colorReader.readLine().toLowerCase();
            } catch (IOException e) {
                e.printStackTrace();
            }

            pickedCard.setColor(choice);
        }

        //We automatically say uno if we have one card
        if(playerHand.size() == 1){
            sayUno();
        }

        return pickedCard;
    }

    /**
     * When the Human Player says uno, they should only have one card left,
     * otherwise they receive a penalty
     */
    @Override
    public void sayUno() {
        if (checkCardCount() == 1){
            System.out.println("UNO!!!");
        } else{
            System.out.println("FOUL ON THE PLAY");
        }
    }

    @Override
    public int checkCardCount() {
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
    public String toString() {
        return "HumanPlayer{" +
                "name='" + name + '\'' +
                '}';
    }


}
