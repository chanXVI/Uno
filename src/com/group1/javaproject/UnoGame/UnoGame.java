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
    public static UnoCard topCard;
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
        setGameDesign();
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
            if(lastCardPlayed.getNumber().contains("wild+4")){
                players.get(turn).draw(4);
                System.out.println(players.get(turn) + " has to draw 4 and their turn is skipped.");
                skip();
                return;
            }else if(lastCardPlayed.getNumber().contains("+2")){
                players.get(turn).draw(2);
                System.out.println(players.get(turn) + " has to draw 2 and their turn is skipped.");
                skip();
                return;
            }else if(lastCardPlayed.getNumber().contains("skip")){
                skip();
                return;
            }

        }


        //player plays their card
        try{
            lastCardPlayed = players.get(turn).playCard();
        }catch(IOException e){
            e.printStackTrace();
        }

        //if did not draw, we check if they played a reverse card, so we know what turn is next
        if(lastCardPlayed != null){
            if(lastCardPlayed.getNumber().contains("reverse")){
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
        System.out.println(players.get(turn) + " was skipped!");
        lastCardPlayed = null;
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
            turn--;
        }else{
            if(turn == players.size()-1){
                //set to -1 so first player in list will have a turn after increment
                turn = -1;
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
        // design the console when game starts
    public void setGameDesign(){
        System.out.println("" +
                "55555555555555555555555555555555555555555555555555555555555555555555555555555555\n" +
                "5555555555555555555555555555555555555555555555555555555555555PP555Y5PGPBBB555555\n" +
                "5555555555555555555555555555555555555555555555555555555555P5YJJ????~5BPGGP555555\n" +
                "555555555555555555555555555555555555555555555555555555555PJ7JY7!!~!J!55555555555\n" +
                "55555555555555555555555555555555555555555555555PP55555555!?GGG!:^^:7?7P555555555\n" +
                "555555555555555555555555555555555555555555555P5YJ????????^?B5GG~^^^:J!?P55555555\n" +
                "5555555555555555555555555555555555555PP5P5P5J?777!!~~~~~!7?B#GBP^^^^^Y~YP5555555\n" +
                "555555555555555555555555555555555P55YJ??7YJ!JJ!^^::^^~^^:^^~YB&&Y:^^^~Y~55555555\n" +
                "55555555555555555555555555PP555PY???77!7J^?BY^^^^?P#&&#P?^^^:!G&&J:^^^7J!5555555\n" +
                "55555555555555555555PP555YJJ55P?7YGG~^^:?G@P:^^^5P55G&#GGG!^^^~B&#7:^^:?77P55555\n" +
                "555555555555555PP55YJ??777777?J^JB5BP:^^:5@?:^^!J~YY?!JBB#G^^^:J&&B!~!775!JP5555\n" +
                "55555555PP555P5J??777?#?^^^^~777^P#B#J:^^^GY:^^~Y~5PP5!J##&!^^:7&#&B?!~^^Y~Y5555\n" +
                "555PP555YJYP5J7JPG~^^:Y#~^^^^^^~!JB&@@7:^^~G~^^^!J!J55J^&@P^^^:YB&#&5::^!YJ~5555\n" +
                "5P5J???77J~JP~JB5BY:^^:PG^^^^:^::^^7Y##!^^:7G~:^^~777?7JP?^^^^??^B&&&PPBGJ7Y5555\n" +
                "5J7JBY~~^~Y~5Y~P#B#?:^^^#5:^^^YP7~:::^!!:^^:J#J^:^^^~~~^::^^!J77?!BBP5J??Y5P5555\n" +
                "Y^G@@#~^^^!J~PJ~B#@&!^^^!&?:^^^B@#P?~^:^^^^^:5@#5?!~^^^^~7J5Y7JPP77?JY55P5555555\n" +
                "577@@@B^^^:?77P77@@@B^^^:?&!^^^!&@@@&GJ~^^^^^^G@@@@&#####GY7?5P5555PP55555555555\n" +
                "55~J@@@5:^^^J!?P!J@@@P^^^:P#^^^:?BP&@@@&GJ!^~!YGY5PPP5YJ??J5P5555555555555555555\n" +
                "555~P#PGJ:^^^Y~Y5~P@@&~^^:7@P^^^:J~^JP#@@@@##GJ7JJJJJJY55PP555555555555555555555\n" +
                "55PY~GGB#7:^^~J7?7!@&Y^^^:J@@Y:^^!P^?J7?5PYJ??J5P5PPP555555555555PPPP55555555555\n" +
                "555P?!#B#&7:^^^!77??~^^^^7B@@@PPBG?7YP5YJJY5PPPPPP5555555555555P5J?JY5PPPPP55555\n" +
                "5555P77&@@@5~^^::^:::^~7?7!##GPY?7YPPPPP55YJ?7!~^~5P5555555555P5: .^^^^~7?Y55PPP\n" +
                "55555577B@@@&GYJ???YPG57?Y?7?JYY55YJ?7!~~!7?Y5PG5:^P5555555555P^ JB#BBPY?!^^^~!?\n" +
                "555555PY7?P#&@@@@&BPY??YP5PPJ7!~!!7?J5PB##G5YJJJ5J JP5PPPPPP5P? 7BPY?YP5J5#BG5J7\n" +
                "555555555YJ????????JY5P555P? ^PBGPPB&&&BY7?YYYYY?!^!5YYYYYYYP5 ^#GYJ75?~~J#&&&&&\n" +
                "5555555555PP555555PP555555P5.7&GY55Y##Y!JG7.:~~~!!!~~~~!!!!!5^ P&#BGBGPYYPG5J?!!\n" +
                "555555555555555555555555PPPB7.G5^7JG#77PBB.:Y5JJ7755555YJ??G7 ?&##&&&B57~:      \n" +
                "555555555555555555555PP5J!~BB.!#55B#!?GPB#:~P!???!J5J7!7?J5Y ~##&#BY~.          \n" +
                "55555555555555555PPPY?~^~?5G&J P&&#77GGPB#:^PJ!?JJJ!!JY55PG:.G&#5!.  ^^:.   .J?!\n" +
                "55555555555555PP5J!^~7YPPGY!B#:~#&Y~PGGPB#:^55P5J!!Y55555B! Y#Y^   .P#GP5YJ!Y&!^\n" +
                "5555555555555PJ~^~?5YJYPPJ~5G@Y 5#~YGGGPB#:^5557~J555555GY !Y^     J@GPPGPGP&?^^\n" +
                "555555555555P5  YG5?PJ7PY^5PP#&^^5~YY?7!J#:^5Y~!555Y7!!?P. ^ .7?!^!&BPPBGGGBG!^^\n" +
                "555555555555P#Y.!PG5GGGP~?PG?~B5 ::^^^^^?#:^Y~755P7^J5PB~    Y&P55PBPPGGPPPPGP~^\n" +
                "555555555555B&&P:^5GPPP5^PPBP:?&! :^^^^^?#^:~755GY.5Y!J?    7&P5555GBBBGGGGGPJY5\n" +
                "5555555555555G#&B~.JPPPJ~PPPBG!BP..^^^^^7#^ ~555GP.::YP.   :#B555555B&GPPGPG~  .\n" +
                "55555555555555G#&#?.7PG?!PPG~: 7&7 ^^^^^7#^ J5555GP?7P^    P&P5555P?B#BBGGG?    \n" +
                "555555555555555PB&&5.~PJ~PPBY. .PB..:^^^7#^:Y555555P#7    .!J5PGG57 .:~7JJ7. .~Y\n" +
                "5555555555555555PB#&G:^J^5PP#G: !&? ^::^7#~:Y555555P5 :.      .:^:         .7P#&");
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

    private UnoGame(){};

    public static UnoGame getInstance(){
        return UnoGameHolder.instance;
    }

    private static class UnoGameHolder {

        private static UnoGame instance = new UnoGame();

    }

}
