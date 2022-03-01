package com.group1.javaproject.Client;

import com.group1.javaproject.UnoGame.UnoGame;

public class UnoGameClient {
    public static void main(String[] args) {
        UnoGame game1 = new UnoGame();
        game1.setRules();
        game1.gameStart();
    }

}
