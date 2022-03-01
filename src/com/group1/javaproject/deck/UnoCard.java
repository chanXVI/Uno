package com.group1.javaproject.deck;

/**
 * The focus of the UnoGame class. Each card has a number and a color
 *
 * @see com.group1.javaproject.players.Player
 * @see com.group1.javaproject.UnoGame.UnoGame
 * @see Deck
 */
public class UnoCard {

    // properties
    String number;
    String color;
    // constructors
    public UnoCard(String number, String color){
        setNumber(number);
        setColor(color);
    }
    // methods

    /**
     * @return the number on this card as a String
     */
    public String getNumber() {
        return number;
    }

    /**
     * Change the number on this card
     * @param number the number that will update this card
     */
    public void setNumber(String number) {
        this.number = number;
    }
    /**
     * @return the color of this card as a String
     */
    public String getColor() {
        return color;
    }
    /**
     * Change the color of this card
     * @param color the color that will update this card
     */
    public void setColor(String color) {
        this.color = color;
    }
    // toString
    @Override
    public String toString(){
        return "Uno Card: "+getColor() + " " + getNumber();
    }
}
