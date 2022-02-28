package com.group1.javaproject.deck;

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
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    // toString
    @Override
    public String toString(){
        return "Uno Card: "+getColor() + " " + getNumber();
    }
}
