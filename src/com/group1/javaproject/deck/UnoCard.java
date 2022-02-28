package com.group1.javaproject.deck;

class UnoCard {

    // properties
    String number;
    String color;
    // constructors
    UnoCard(String number, String color){
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
}

