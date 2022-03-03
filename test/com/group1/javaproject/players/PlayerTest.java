package com.group1.javaproject.players;

import com.group1.javaproject.UnoGame.UnoGame;
import com.group1.javaproject.deck.UnoCard;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {
    String BLACK_LETTERS = "\u001B[30m";
    String BACKGROUND_COLOR_BLUE = "\u001B[44m";
    String BACKGROUND_COLOR_RED = "\u001B[41m";
    String BACKGROUND_COLOR_YELLOW = "\u001B[43m";
    String BACKGROUND_COLOR_GREEN = "\u001B[42m";
    String ANSI_RESET = "\u001B[0m";
    Player tester;

    @Before
    public void innit(){
        //top card should be pre-decided before testing
        UnoGame.topCard = new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"4" + ANSI_RESET, "green");
        //an instance of player needed to test the method
        tester = new AiPlayer("tester", 7);
    }

    /**
     * Test that the isValid() method is accurately determining valid cards
     */
    @Test
    public void testIsValid(){
        //same number and color, should be valid
        assertTrue(tester.isCardValid(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"4" + ANSI_RESET, "green")));
        //same number, different color, should still be valid
        assertTrue(tester.isCardValid(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"4" + ANSI_RESET, "blue")));
        //different number, same color, should still be valid
        assertTrue(tester.isCardValid(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"7" + ANSI_RESET, "green")));
        //wild card used, should still be valid
        assertTrue(tester.isCardValid(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS +"+2" + ANSI_RESET, "wild")));
        //different number and color, only case that won't work
        assertFalse(tester.isCardValid(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS + "5" + ANSI_RESET, "red")));
    }

    /**
     * Test that the AI is returning a valid card
     * DONE: Assert that the card returned is valid
     */
    @Test
    public void testPlayCard() {
        List<UnoCard> workingHand = new ArrayList<>();
        workingHand.add(new UnoCard(BACKGROUND_COLOR_YELLOW + BLACK_LETTERS +"5" + ANSI_RESET, "yellow"));
        workingHand.add(new UnoCard(BACKGROUND_COLOR_BLUE + BLACK_LETTERS+"5" + ANSI_RESET, "blue"));
        workingHand.add(new UnoCard(BACKGROUND_COLOR_GREEN + BLACK_LETTERS +"5" + ANSI_RESET, "green")); //only valid card in this hand
        workingHand.add(new UnoCard(BACKGROUND_COLOR_RED + BLACK_LETTERS + "9" + ANSI_RESET, "red"));
        tester.setHand(workingHand);

        try{
            //the card being played should be valid
            assertTrue(tester.isCardValid(tester.playCard()));
        } catch(IOException e){
            e.printStackTrace();
        }

        try{
            //the only valid card should be removed, calling playCard again should return null
            assertNull(tester.playCard());
            //After drawing again, tester should have 4 cards
            assertEquals(tester.checkCardCount(), 4);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}