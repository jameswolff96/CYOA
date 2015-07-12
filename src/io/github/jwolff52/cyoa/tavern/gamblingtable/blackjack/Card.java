package io.github.jwolff52.cyoa.tavern.gamblingtable.blackjack;
public class Card {
    private int cardNumber;
    private int cardValue;
    private String card;
    private boolean valueSet=false;
    public int getNumber(){
        return cardNumber;
    }
    public int getValue(){
        return cardValue;
    }
    public String getCard(){
        return card;
    }
    public void setNumber(){
        cardNumber=Draw.draw();
    }
    public void setValue(boolean player){
        if(!valueSet){
            if(player){
                cardValue=Formats.playerFormat(cardNumber);
                valueSet=true;
            }else{
                cardValue=Formats.dealerFormat(cardNumber);
                valueSet=true;
            }
        }
    }
    public void setCard(boolean player){
        if(player){
            card=Formats.Card(cardNumber);
        }else{
            card=Formats.Card(cardNumber);
        }
    }
}
