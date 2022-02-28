package com.group1.javaproject.players;

import com.group1.javaproject.deck.Deck;
import com.group1.javaproject.deck.UnoCard;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer implements Player{

    private String name;
    private boolean isHuman = true;

    public HumanPlayer(String name){
        this.name = name;
        playerHand.addAll(Deck.dealCards());
    }

    @Override
    public void draw() {
        playerHand.addAll(Deck.drawCards(1));
    }

    /**
     * Ask which card should be played, until a valid card is chosen
     * @return the card the player chose
     */
    @Override
    public UnoCard playCard() {
        Scanner cardChoice = new Scanner(System.in);
        boolean validPresent = false;
        int choice;
        for(int i = 0; i < playerHand.size(); i++){
            System.out.println((i+1) + ": " + playerHand.get(i));
            if(isCardValid(playerHand.get(i))){
                validPresent = true;
            }
        }
        do{
            System.out.println("Enter a number for which card you would like to play");
            choice = cardChoice.nextInt();

        }while(choice > playerHand.size() || isCardValid(playerHand.get(choice)));


        return playerHand.get(choice);
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
