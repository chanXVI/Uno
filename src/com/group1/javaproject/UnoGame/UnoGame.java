package com.group1.javaproject.UnoGame;

import com.group1.javaproject.deck.Deck;
import com.group1.javaproject.deck.UnoCard;
import com.group1.javaproject.players.AiPlayer;
import com.group1.javaproject.players.HumanPlayer;
import com.group1.javaproject.players.Player;

import java.io.IOException;
import java.util.*;

/**
 * The UnoGame allows 2-4 players to play a game of uno. Each player can be either Human or an AI with automatic responses.
 * Each Player will use UnoCards to try to bring their card count down to 0.
 *
 * TODO: Implement turns
 *
 * @see Player
 * @see Deck
 * @see UnoCard
 * @author Team Uno
 */
public class UnoGame {
    // properties
    ArrayList<Player> players = new ArrayList<>();
    public static UnoCard topCard;
    public static UnoCard lastCardPlayed = null;
    private int startingHand;
    private int numberOfPlayers;
    private int numberOfHumanPlayers;
    private int numberOfAiPlayers;
    Scanner input = new Scanner(System.in);

    // business methods

    /**
     * The rules for this game need to be decided.
     * Prompts user for the number of starting cards, how many players,
     * how many of them are human, and what the human player names are.
     */
    public void setRules() {
        setStartingCards();
        setNumberOfPlayers();
        setPlayerTypes();
        setPlayerNames();
    }

    /**
     * Starts the game of Uno!
     * Will iterate through each player, and have them play a card
     */
    public void gameStart() {
        System.out.println("\n***UNO GAME HAS STARTED***\n");
        Collections.shuffle(players);
        Collection<UnoCard> startCard = Deck.drawCards(1);
        topCard = startCard.iterator().next();
//        System.out.println(topCard);
//        for (Player player: players){
        for (int x = 0; x < players.size(); x++){
            try {
                lastCardPlayed = players.get(x).playCard();
            }catch (IOException e){
                System.out.println(e);
            }
           if (lastCardPlayed != null){
               topCard = lastCardPlayed;
           }
            if (players.get(x).checkCardCount() == 1){
                players.get(x).sayUno();
            }
        }
    }

    /**
     * A player with 0 cards will win the game!
     * TODO: check each player card count to see if anyone has won
     * @return a boolean stating if the game has been won or not
     */
    public static boolean gameWon(Player player){
        if (player.checkCardCount() == 0){
            System.out.println(player + " has won the game!!!!!!!!!!!!!!!");
            return true;
        } else{
            System.out.println(player + "has not won the game. Keep playing!");
            return false;
        }

    }

    /**
     * Checks the number of cards each player has
     * TODO: Either add parameter for Player, or change return type to void
     * TODO: Implement method
     * @return
     */
    public void checkCards(){
        for (Player player : players){
            System.out.println(player + "has " + player.checkCardCount() + "cards.");
        }
    }

    /**
     * Creates each player for this game of Uno!
     * Note: may not be needed, depending on implementation
     */
    public void createPlayers(Player player){
        players.add(player);
    }


    // sub methods for gameStart method: setStartingCards, setNumberOfPlayers, setPlayerTypes, SetPlayerNames

    /**
     * The user decides how many cards each player should start with, choosing a number between 7-10
     */
    public void setStartingCards() {
        // number of cards in the starting hand?
        do {
            System.out.println("Enter the numbers of cards in the starting hand (between 7 - 10): ");
            this.startingHand = Integer.parseInt(input.nextLine());
        } while (startingHand < 7 || startingHand > 10);
    }

    /**
     * The user decides how many players are playing in this game of Uno
     */
    public void setNumberOfPlayers() {
        // number of players to play?
        do {
            System.out.println("Enter the number of players (between 2-4): ");
            this.numberOfPlayers = Integer.parseInt(input.nextLine());
        } while (numberOfPlayers < 2 || numberOfPlayers > 4);
    }

    /**
     * Identifies how many of the users selected are human, and how many are AI
     */
    public void setPlayerTypes() {
        // how many players are human?
        do {
            System.out.println("Out of " + numberOfPlayers + " players, how many are human players? ");
            this.numberOfHumanPlayers = Integer.parseInt(input.nextLine());
        } while (numberOfHumanPlayers < 1 || numberOfHumanPlayers > numberOfPlayers);

        // how many players are Ai?
        this.numberOfAiPlayers = numberOfPlayers - numberOfHumanPlayers;
    }

    /**
     * Set the name of each player in the game, easier to identify who's turn it is and who does what.
     * Iterates through each human player and prompts for a name, and automatically fills the AI names
     */
    public void setPlayerNames() {
        String HumanPlayerNames[] = new String[numberOfHumanPlayers];
        String AiPlayerNames[] = new String[numberOfAiPlayers];

        // names of the human player
        int x = 0;
        while (x < numberOfHumanPlayers) {
            System.out.println("Enter the name of player " + (x + 1) + ": ");
            HumanPlayerNames[x] = input.nextLine();
            x++;
        }
        // names of AI players
        int y = 0;
        while (y < numberOfAiPlayers) {
            AiPlayerNames[y] = "AI Player - " + (y + 1);
            y++;
        }
        // add total players (human and Ai) to the Arraylist;
        for (String player : HumanPlayerNames) {
            players.add(new HumanPlayer(player, startingHand));
        }
        for (String aiPlayer : AiPlayerNames) {
            players.add(new AiPlayer(aiPlayer, startingHand));
        }
    }
}
