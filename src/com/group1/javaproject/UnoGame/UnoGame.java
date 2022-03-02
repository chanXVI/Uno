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
 * @see Player
 * @see Deck
 * @see UnoCard
 * @author Team Uno
 */
public class UnoGame implements HasTurns{
    // properties
    List<Player> players = new ArrayList<>();
    public static UnoCard topCard = new UnoCard("wild+4", "wild");
    public static UnoCard lastCardPlayed = null;
    private int startingHand;
    private int numberOfPlayers;
    private int numberOfHumanPlayers;
    private int numberOfAiPlayers;
    private int turn;
    private boolean reversed = false;
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
        Collections.shuffle(players);
        Collection<UnoCard> startCard = Deck.drawCards(1);
        topCard = startCard.iterator().next();
        //System.out.println(topCard);

        //time to play the game
        while(!gameWon()){
            startTurn();
            nextTurn();
        }
    }

    /**
     * A player with 0 cards will win the game!
     * @return a boolean stating if the game has been won or not
     */
    public boolean gameWon(){
        boolean won = false;
        for(Player player : players)
            if (player.checkCardCount() == 0){
            System.out.println(player + " has won the game!!!!!!!!!!!!!!!");
            won = true;
        }
        return won;

    }

    /**
     * Checks the number of cards each player has
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

    //turn methods: subject to change for now, just testing implementation

    /**
     * The start of a new turn.
     */
    public void startTurn(){

        //if the last card was a draw card, the next player needs to draw and their turn is skipped
        if(lastCardPlayed != null){
            if(lastCardPlayed.getNumber().equals("wild+4")){
                players.get(turn).draw(4);
                System.out.println(players.get(turn) + " has to draw 4 and their turn is skipped.");
                skip();
            }else if(lastCardPlayed.getNumber().equals("+2")){
                players.get(turn).draw(2);
                System.out.println(players.get(turn) + " has to draw 2 and their turn is skipped.");
                skip();
            }
        }

        //last card played is now null, so we don't force everyone to draw
        lastCardPlayed = null;

        //player plays their card
        try{
            lastCardPlayed = players.get(turn).playCard();
        }catch(IOException e){
            e.printStackTrace();
        }

        //if did not draw, we check if they played a reverse card, so we know what turn is next
        if(lastCardPlayed != null){
            if(lastCardPlayed.getNumber().equals("reverse")){
                reverse();
            }
            //only updating the top card if a card was actually played
            topCard = lastCardPlayed;
        }
    }

    /**
     * Reverses the order of turns
     */
    public void reverse(){
        if(reversed){
            reversed =  false;
        }else{
            reversed = true;
        }
    }

    /**
     * A player has had their turn skipped.
     */
    public void skip(){
        if(reversed){
            turn--;
        }else{
            turn++;
        }
    }

    /**
     * Determines what turn is next, based on turn order and if someone has been skipped.
     */
    public void nextTurn(){
        if(reversed){
            if(turn == 0){
                //one larger than the player size, so that the next -- will bring the next turn to the last player in the collection
                turn = players.size();
            }
            if(topCard.getNumber().equals("skip")){
                skip();
            }
            turn--;
        }else{
            if(turn == players.size()-1){
                //set to -1 so first player in list will have a turn after increment
                turn = -1;
            }
            if(topCard.getNumber().equals("skip")){
                skip();
            }
            turn++;
        }
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
        for(String player : AiPlayerNames){
            players.add(new AiPlayer(player, startingHand));
        }

    }

    //Methods created for testing

    /**
     * A method made for testing UnoGame
     * @param starting the amount of cards to start with
     */
    public void setStartingCards(int starting){
        this.startingHand = starting;
    }

    /**
     * A method created for testing without using Scanners
     * @param numberOfPlayers the number of players to create
     */
    public void setNumberOfPlayers(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }

    public void setNumberOfHumanPlayers(int numberOfHumanPlayers){
        this.numberOfHumanPlayers = numberOfHumanPlayers;
        this.numberOfAiPlayers = numberOfPlayers - numberOfHumanPlayers;
    }

    public void setPlayerNames(String ... names){
        String HumanPlayerNames[] = new String[numberOfHumanPlayers];
        String AiPlayerNames[] = new String[numberOfAiPlayers];

        // names of the human player
        int x = 0;
        while (x < numberOfHumanPlayers) {
            HumanPlayerNames[x] = "Human Player " + x;
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
        for(String player : AiPlayerNames){
            players.add(new AiPlayer(player, startingHand));
        }
    }

    public List<Player> getPlayers(){
        return players;
    }

}
