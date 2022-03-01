package com.group1.javaproject.players;

import com.group1.javaproject.deck.Deck;
import com.group1.javaproject.deck.UnoCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class HumanPlayer implements Player{

    private String name;
    private boolean isHuman = true;

    public HumanPlayer(String name){
        this.name = name;
        playerHand.addAll(Deck.dealCards());
    }

    @Override
    public void draw(int amount) {
        playerHand.addAll(Deck.drawCards(amount));
    }

    @Override
    public UnoCard playCard() throws IOException {
        System.out.println(playerHand);

        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        System.out.println("What card do you want to play?");
        String card = reader.readLine(); //enter place in arraylist to get element
        System.out.println("You picked:");
        int x = Integer.parseInt(card); //entered number will be changed to int
        System.out.println(playerHand.get(x)); //int x used to get UnoCard from "index x" of playerHand
        playerHand.remove(x); //remove from hand

        return playerHand.get(x);
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

    //HOLD
    @Override
    public void reverse() {

    }

    //HOLD
    @Override
    public void skip() {

    }

    @Override
    public int checkCardCount() {
        return Player.super.checkCardCount();
    }

    @Override
    public boolean isCardValid(UnoCard card) {
        return Player.super.isCardValid(card);
    }

    @Override
    public String toString() {
        return "HumanPlayer{" +
                "name='" + name + '\'' +
                ", isHuman=" + isHuman +
                '}';
    }
}
