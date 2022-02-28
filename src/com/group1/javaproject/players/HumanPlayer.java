package com.group1.javaproject.players;

import com.group1.javaproject.deck.Deck;
import com.group1.javaproject.deck.UnoCard;

import java.util.List;

public class HumanPlayer implements Player{

    private String name;
    private boolean isHuman = true;

    public HumanPlayer(String name){
        this.name = name;
        playerHand.add(Deck.dealCards());
    }

    @Override
    public void draw() {
        playerHand.add(Deck.drawCards(1));
    }

    @Override
    public UnoCard playCard(UnoCard card) {
        return card;
    }

    //yell uno when hand = 1
    @Override
    public void sayUno() {
        if (checkCardCount() == 1){
            System.out.println("UNO!!!");
        } else{
            System.out.println("FOUL ON THE PLAY");
        }
    }

    @Override
    public void reverse() {

    }
    @Override
    public void skip() {

    }
}
