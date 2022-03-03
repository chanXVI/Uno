package com.group1.javaproject.Client;

import com.group1.javaproject.UnoGame.UnoGame;

public class UnoGameClient {
    public static void main(String[] args) {
       UnoGame game = UnoGame.getInstance();
       game.setRules();
       game.gameStart();
    }

}
