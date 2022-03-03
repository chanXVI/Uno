package com.group1.javaproject.UnoGame;

import com.group1.javaproject.deck.UnoCard;
import com.group1.javaproject.players.Player;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UnoGameTest {
    static String BLACK_LETTERS = "\u001B[30m";
    static String BACKGROUND_COLOR_WHITE = "\u001B[47m";
    static String ANSI_RESET = "\u001B[0m";

    @BeforeClass
    public static void innit(){

        UnoGame game = UnoGame.getInstance();

        game.setStartingCards(5);
        game.setNumberOfPlayers(5);
        game.setNumberOfHumanPlayers(0);
        game.setPlayerNames(""); //Using parameter to avoid prompts
        //List of players to access from tests
        List<Player> players;
        players = game.getPlayers();

        //We need to know what the top card is. Set to wild +4 to test that the first player doesn't draw right away
        UnoGame.topCard = new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS + "wild+4", "wild");
    }

    @Test
    public void testTurns(){
        UnoGame game = UnoGame.getInstance();
        List<Player> players = game.getPlayers();
        //turns should go in order
        for(int i = 0; i < players.size(); i++){
            assertEquals(game.getTurn(), i);
            game.nextTurn();
        }
        //should have looped back to the start
        assertEquals(0, game.getTurn());

        //reverse order and go to next turn
        game.reverse();
        game.nextTurn();

        //should now be last players turn
        assertEquals(game.getTurn(), players.size() - 1);

        //test that this continues through the next round
        for(int i = players.size() - 1; i >= 0; i--){
            assertEquals(game.getTurn(), i);
            game.nextTurn();
        }
    }

    @Test
    public void testDraw(){
        UnoGame game = UnoGame.getInstance();
        List<Player> players = game.getPlayers();
        for(int i = 0; i < 3; i++){
            game.checkCards();
            game.startTurn();
            game.nextTurn();
        }
        assertTrue(players.get(1).checkCardCount() > 2);

    }

    @Test
    public void testSkip(){
        UnoGame game = UnoGame.getInstance();
        List<Player> players = game.getPlayers();
        List<UnoCard> skip = new ArrayList<>();
        skip.add(new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS + "skip" + ANSI_RESET, "red"));
        skip.add(new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS + "skip" + ANSI_RESET, "red"));
        skip.add(new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS + "skip" + ANSI_RESET, "red"));

        for(Player player : players){
            player.setHand(skip);
        }

        UnoGame.topCard = new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS +"3" + ANSI_RESET, "red");

        game.setTurn(0);

        if(game.isReversed()){
            game.reverse();
        }

        game.startTurn();
        assertTrue(UnoGame.topCard.getNumber().contains("skip"));
        game.nextTurn();
        game.startTurn();
        //last card played should be set to null after previous turn
        assertNull(UnoGame.lastCardPlayed);
        game.nextTurn();
        assertEquals(game.getTurn(), 2);
    }

    @Test
    public void testReverse(){
        UnoGame game = UnoGame.getInstance();
        List<Player> players = game.getPlayers();
        List<UnoCard> reverse = new ArrayList<>();
        reverse.add(new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS + "reverse" + ANSI_RESET, "red"));
        reverse.add(new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS + "reverse" + ANSI_RESET, "red"));
        reverse.add(new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS + "reverse" + ANSI_RESET, "red"));

        for(Player player : players){
            player.setHand(reverse);
        }

        UnoGame.topCard = new UnoCard(BACKGROUND_COLOR_WHITE + BLACK_LETTERS +"3" + ANSI_RESET, "red");

        game.setTurn(0);

        game.startTurn();
        //game should be reversed after a reverse card is played
        assertTrue(game.isReversed());
        game.nextTurn();
        game.startTurn();
        //game should no longer be reversed after another reverse card is played.
        assertFalse(game.isReversed());
    }
}