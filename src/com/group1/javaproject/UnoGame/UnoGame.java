package com.group1.javaproject.UnoGame;

import com.group1.javaproject.deck.Deck;
import com.group1.javaproject.deck.UnoCard;
import com.group1.javaproject.players.AiPlayer;
import com.group1.javaproject.players.HumanPlayer;
import com.group1.javaproject.players.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class UnoGame {
    // properties
    ArrayList<Player> players = new ArrayList<>();
    public static UnoCard topCard = new UnoCard("4", "green");
    private int startingHand;
    private int numberOfPlayers;
    private int numberOfHumanPlayers;
    private int numberOfAiPlayers;
    Scanner input = new Scanner(System.in);

    // business methods
    public void setRules() {
        setStartingCards();
        setNumberOfPlayers();
        setPlayerTypes();
        setPlayerNames();
    }
    public void gameStart(){
        for (Player player: players){
            System.out.println(player.checkCardCount());
        }
    }
    public boolean gameWon(){
        return true;
    }
    public int checkCards(){
        return 0;
    }
    public void createPlayers(){

    }
    public void dealCards(){

    }

            // sub methods for gameStart method: setStartingCards, setNumberOfPlayers, setPlayerTypes, SetPlayerNames
        public void setStartingCards() {
            // number of cards in the starting hand?
            do {
                System.out.println("Enter the numbers of cards in the starting hand (between 7 - 10): ");
                this.startingHand = Integer.parseInt(input.nextLine());
            } while (startingHand < 7 || startingHand > 10);
        }

        public void setNumberOfPlayers() {
            // number of players to play?
            do {
                System.out.println("Enter the number of players (between 2-4): ");
                this.numberOfPlayers = Integer.parseInt(input.nextLine());

            } while (numberOfPlayers < 2 || numberOfPlayers > 4);
        }

        public void setPlayerTypes() {
            // how many players are human?
            do {
                System.out.println("Out of " + numberOfPlayers + " players, how many are human players? ");
                this.numberOfHumanPlayers = Integer.parseInt(input.nextLine());
            } while (numberOfHumanPlayers < 1 || numberOfHumanPlayers > numberOfPlayers);

            // how many players are Ai?
            this.numberOfAiPlayers = numberOfPlayers - numberOfHumanPlayers;
        }
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
