package com.group1.javaproject.Client;

import com.group1.javaproject.UnoGame.UnoGame;
import com.group1.javaproject.players.AiPlayer;
import com.group1.javaproject.players.HumanPlayer;

public class UnoGameClient {
    public static void main(String[] args) {
        /**
         * Uses Command Parser
         */
        if (args.length > 0) {
            int aiPlayerVal = 1;
            int startingCardsInHand = Integer.parseInt(args[0]);
            UnoGame game1 = UnoGame.getInstance();
            for (int num = 1; num < args.length; num++) {
                if (args[num].equalsIgnoreCase("Ai")) {
                    game1.players.add(new AiPlayer("AI Player - " + aiPlayerVal, startingCardsInHand));
                    aiPlayerVal++;
                } else {
                    game1.players.add(new HumanPlayer(args[num], startingCardsInHand));
                }
            }
            game1.gameStart();
        } else {
            /**
             * Using System.in
             */
            UnoGame game1 = UnoGame.getInstance();
            game1.setRules();
            game1.gameStart();
        }
    }

}
